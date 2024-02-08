package com.hororok.monta.controller;

import com.hororok.monta.dto.request.item.PatchItemRequestDto;
import com.hororok.monta.dto.request.item.PostItemRequestDto;
import com.hororok.monta.handler.CustomValidationException;
import com.hororok.monta.service.ItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v2")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/admin/items")
    public ResponseEntity<?> postItem(@Valid @RequestBody PostItemRequestDto requestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for(FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getField() + " : " + error.getDefaultMessage());
            }
            throw new CustomValidationException(errors);
        } else {
            return itemService.postItem(requestDto);
        }
    }

    @GetMapping("/admin/items")
    public ResponseEntity<?> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/admin/items/{itemId}")
    public ResponseEntity<?> getItem(@PathVariable int itemId) {
        return itemService.getItem(itemId);
    }

    @PatchMapping ("/admin/items/{itemId}")
    public ResponseEntity<?> patchItem(@Valid @RequestBody PatchItemRequestDto requestDto, @PathVariable int itemId, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for(FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getField() + " : " + error.getDefaultMessage());
            }
            throw new CustomValidationException(errors);
        } else {
            return itemService.patchItem(requestDto, itemId);
        }
    }

    @DeleteMapping ("/admin/items/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable int itemId) {
        return itemService.deleteItem(itemId);
    }

    @PostMapping("/item-inventory/{itemInventoryId}")
    public ResponseEntity<?> useItem(@PathVariable Long itemInventoryId) {
        return itemService.useItem(itemInventoryId);
    }
}