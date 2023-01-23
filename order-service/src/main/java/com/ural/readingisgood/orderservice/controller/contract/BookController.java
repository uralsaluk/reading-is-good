package com.ural.readingisgood.orderservice.controller.contract;

import com.ural.readingisgood.orderservice.controller.model.request.CreateBookControllerRequest;
import com.ural.readingisgood.orderservice.controller.model.request.UpdateInventoryControllerRequest;
import com.ural.readingisgood.orderservice.controller.model.response.CreateBookControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.GetBookControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.UpdateInventoryControllerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("api/v0/book")
public interface BookController {


    @PostMapping()
    ResponseEntity<CreateBookControllerResponse> createBook(@RequestBody @Valid CreateBookControllerRequest request);

    @PutMapping("{id}")
    ResponseEntity<UpdateInventoryControllerResponse> updateInventory(@PathVariable(required = true) Long id,
                                                                      @RequestBody UpdateInventoryControllerRequest request);

    @GetMapping()
    ResponseEntity<GetBookControllerResponse> getBook(@RequestParam(required = true) String name
    );


}
