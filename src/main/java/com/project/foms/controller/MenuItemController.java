package com.project.foms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.foms.dto.ApiResponse;
import com.project.foms.dto.menuItemdto.MenuItemRequestDto;
import com.project.foms.dto.menuItemdto.MenuItemResponseDto;
import com.project.foms.service.menuItemService.MenuItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/menuitem")
public class MenuItemController {

    private final MenuItemService service;

    public MenuItemController(MenuItemService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<MenuItemResponseDto>> createMenuItem(@Valid @RequestBody MenuItemRequestDto m) {
        MenuItemResponseDto menuItem = service.createMenuItem(m);
        ApiResponse<MenuItemResponseDto> response = new ApiResponse<MenuItemResponseDto>(HttpStatus.CREATED.value(),
                "Menu item created sussesfully", menuItem);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<MenuItemResponseDto>>> getAllMenuItem() {
        List<MenuItemResponseDto> menuItems = service.getAllMenuItems();
        ApiResponse<List<MenuItemResponseDto>> response = new ApiResponse<List<MenuItemResponseDto>>(
                HttpStatus.OK.value(), "All menu item fetched sussesfully", menuItems);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/id/{itemId}")
    public ResponseEntity<ApiResponse<MenuItemResponseDto>> getMenuItemById(@PathVariable int itemId) {
        MenuItemResponseDto menuIem = service.getMenuItemsById(itemId);
        ApiResponse<MenuItemResponseDto> response = new ApiResponse<MenuItemResponseDto>(HttpStatus.OK.value(),
                "MenuItem fetched sussesfully", menuIem);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{itemId}")
    public ResponseEntity<ApiResponse<MenuItemResponseDto>> updateMenuItem(@Valid @PathVariable int itemId,
            @RequestBody MenuItemRequestDto m) {
        MenuItemResponseDto menuItem = service.updateMenuItem(itemId, m);
        return new ResponseEntity<>(
                new ApiResponse<MenuItemResponseDto>(HttpStatus.OK.value(), "MenuItem fetched sussesfully", menuItem),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<ApiResponse<String>> deleteMenuItem(@PathVariable int itemId) {
        service.deleteMenuItem(itemId);
        return new ResponseEntity<>(
                new ApiResponse<String>(HttpStatus.OK.value(), "MenuItem deleted sussesfully", null), HttpStatus.OK);

    }

    @GetMapping("/get/price")
    // GET /menuitems/get/price?price=200
    public ResponseEntity<ApiResponse<List<MenuItemResponseDto>>> findItemGreaterThan(@RequestParam int price) {
        List<MenuItemResponseDto> menuItems = service.findItemGreaterThan(price);
        return new ResponseEntity<>(new ApiResponse<List<MenuItemResponseDto>>(HttpStatus.OK.value(),
                "MenuItem list greater than price", menuItems), HttpStatus.OK);
    }

    @GetMapping("/get/name/{itemName}")
    public ResponseEntity<ApiResponse<MenuItemResponseDto>> getMenuItemByName(@PathVariable String itemName) {
        MenuItemResponseDto menuItem = service.getByItemName(itemName);
        return new ResponseEntity<>(new ApiResponse<MenuItemResponseDto>(HttpStatus.OK.value(),
                "MenuItem list greater than price", menuItem), HttpStatus.OK);
    }
}
