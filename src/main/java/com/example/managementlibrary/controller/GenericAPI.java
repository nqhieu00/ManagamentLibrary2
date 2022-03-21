package com.example.managementlibrary.controller;

import com.example.managementlibrary.common.EOperator;
import com.example.managementlibrary.common.Filter;
import com.example.managementlibrary.exception.ApiError;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.service.GenericService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoRepositoryBean

public abstract class GenericAPI<E,I,D1,D2> {


    GenericService<E,I,D1,D2> genericService;
    public GenericAPI(GenericService<E,I,D1,D2> genericService){
        this.genericService=genericService;
    }


    @GetMapping
    public ResponseEntity<Page<D2>> getPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "filter-field",required = false) String field,
            @RequestParam(value = "filter-operator",required = false) String operator,
            @RequestParam(value = "filter-value",required = false) String value)
            {
        try {
            List<Filter> filters = null;
            if(field!=null&&operator!=null&&value!=null){
                filters=new ArrayList<>();
                String fields[] =field.split("\\,");
                String operators[] =operator.split("\\,");
                String values[] =value.split("\\,");
                for (int i=0;i<fields.length;i++){
                    Filter filter=new Filter();

                    try {
                        filter.setField(fields[i]);
                        filter.setOperator(EOperator.valueOf(operators[i]));
                        filter.setValue(values[i]);
                    }
                    catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e){
                        return new ResponseEntity(new ApiError(HttpStatus.CONFLICT, "Get page failed", e),HttpStatus.CONFLICT);
                    }


                    filters.add(filter);
                }
            }
            return new ResponseEntity(genericService.getPage(page,limit,sort,filters), HttpStatus.OK);
        }
        catch ( GenericException | DataIntegrityViolationException  e){
            //return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT, "Get page failed", e),HttpStatus.CONFLICT);
        }
    }




    @GetMapping(value = "/{id}")
    public ResponseEntity<D2> getOne(@PathVariable I id) {
        try {

            return new ResponseEntity(genericService.get(id),HttpStatus.OK);
        }
        catch (GenericException e){
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT, "Get failed", e),HttpStatus.CONFLICT);

        }


    }

    @PostMapping
    public ResponseEntity<D2> create(@Validated @RequestBody D1 dto){

        try {
            System.out.println(dto.toString());

            return new ResponseEntity(genericService.create(dto),HttpStatus.CREATED);
        }
        catch (GenericException e){
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT, "Create failed", e),HttpStatus.CONFLICT);
          //  return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }



    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<D2> update(@Validated @RequestBody D1 dto,@PathVariable I id){

        try {
            System.out.println(dto.toString());

            return new ResponseEntity( genericService.update(dto,id) ,HttpStatus.OK);
        }
        catch (GenericException e){
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT, "Update failed", e),HttpStatus.CONFLICT);
            //return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable I id){

        try {
            genericService.delete(id);
            return new ResponseEntity("Delete id = "+id+" successful",HttpStatus.OK);
        }
        catch (GenericException  | DataIntegrityViolationException e){
           // return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT, "Delete failed", e),HttpStatus.CONFLICT);
        }
    }

}
