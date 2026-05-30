package com.project.foms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.project.foms.dto.menuItemdto.MenuItemResponseDto;
import com.project.foms.dto.resturantdto.ResturantRequestDto;
import com.project.foms.dto.resturantdto.ResturantResponseDto;
import com.project.foms.service.resturantService.ResturantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "Resturant APIs",
    description = "Operation related to resturant"
)
@CrossOrigin("*")
@RestController
@RequestMapping("/resturant")
public class ResturantController {

    @Autowired
    private ResturantService service;

    @Operation(summary = "This endpoint for create resturant")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ResturantResponseDto>> createResturant(@RequestBody ResturantRequestDto r) {
        ResturantResponseDto resturant = service.createResturant(r);
        ApiResponse<ResturantResponseDto> response = new ApiResponse<ResturantResponseDto>(HttpStatus.CREATED.value(),
                "Resturant Added Sussesfully", resturant);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "This endpoint for get all resturant")
    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<ResturantResponseDto>>> getAllResturant() {
        List<ResturantResponseDto> resturantList = service.getAllResturant();
        ApiResponse<List<ResturantResponseDto>> response = new ApiResponse<List<ResturantResponseDto>>(
                HttpStatus.OK.value(), "All resturant fetched", resturantList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "This endpoint for get resturant by resturantId")
    @GetMapping("/get/id/{resturantId}")
    public ResponseEntity<ApiResponse<ResturantResponseDto>> getResturantById(@PathVariable int resturantId) {
        ResturantResponseDto resturant = service.getResturantById(resturantId);
        ApiResponse<ResturantResponseDto> response = new ApiResponse<ResturantResponseDto>(HttpStatus.OK.value(),
                "Resturant fetched Sussesfully", resturant);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "This endpoint for update resturant")
    @PutMapping("/update/{resturantId}")
    public ResponseEntity<ApiResponse<ResturantResponseDto>> updateResturant(@PathVariable int resturantId,
            @RequestBody ResturantRequestDto r) {
        ResturantResponseDto resturant = service.updateResturant(resturantId, r);
        ApiResponse<ResturantResponseDto> response = new ApiResponse<ResturantResponseDto>(HttpStatus.OK.value(),
                "Resturant Updated Sussesfully", resturant);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "This endpoint for delete resturant")
    @DeleteMapping("/delete/{resturantId}")
    public ResponseEntity<ApiResponse<String>> deleteResturant(@PathVariable int resturantId) {
        service.deleteResturant(resturantId);
        ApiResponse<String> response = new ApiResponse<String>(HttpStatus.OK.value(), "Resturant vanished", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "This endpoint for get resturants by location")
    @GetMapping("/get/location/{location}")
    public ResponseEntity<ApiResponse<List<ResturantResponseDto>>> getResturantByLocation(
            @PathVariable String location) {
        List<ResturantResponseDto> resturantList = service.getResturantByLocation(location);
        ApiResponse<List<ResturantResponseDto>> response = new ApiResponse<List<ResturantResponseDto>>(
                HttpStatus.OK.value(), "Resturant fetched sussesfully", resturantList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "This endpoint for get resturant by resturantName")
    @GetMapping("/get/resturantName/{resturantName}")
    public ResponseEntity<ApiResponse<ResturantResponseDto>> getResturantByName(@PathVariable String resturantName) {
        ResturantResponseDto resturant = service.getResturantByName(resturantName);
        ApiResponse<ResturantResponseDto> response = new ApiResponse<>(HttpStatus.OK.value(),
                "Resturant fetched sussesfully", resturant);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "This endpoint for get menuItems for specific resturant using resturantId")
    @GetMapping("/get/menuItems/{resturantId}")
    public ResponseEntity<ApiResponse<List<MenuItemResponseDto>>> getMenuItemsByResturant(
            @PathVariable int resturantId) {
        List<MenuItemResponseDto> menuList = service.getMenuItemsByResturant(resturantId);
        ApiResponse<List<MenuItemResponseDto>> response = new ApiResponse<>(HttpStatus.OK.value(),
                "Resturant fetched sussesfully", menuList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "This endpoint for get all resturants in page format")
    @GetMapping("/get/all/resturants")
    public ResponseEntity<ApiResponse<List<ResturantResponseDto>>> getResturantByPage(@RequestParam int page,
            @RequestParam int size) {
        List<ResturantResponseDto> resturantList = service.getResturantByPage(page, size);
        ApiResponse<List<ResturantResponseDto>> response = new ApiResponse<List<ResturantResponseDto>>(
                HttpStatus.OK.value(), "Resturant fetched sussesfully", resturantList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
