package com.project.foms.service.menuItemService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.foms.dto.menuItemdto.MenuItemRequestDto;
import com.project.foms.dto.menuItemdto.MenuItemResponseDto;
import com.project.foms.entity.MenuItem;
import com.project.foms.repository.MenuItemRepository;

@Service
public class MenuItemServiceImp implements MenuItemService{
    
    @Autowired
    private MenuItemRepository repo;

    @Override
    public MenuItemResponseDto createMenuItem(MenuItemRequestDto m){
        MenuItem menuItem = new MenuItem();
        menuItem.setItemName(m.getItmeName());
        menuItem.setPrice(m.getPrice());
        menuItem.setAvailability(m.isAvailability());
        MenuItem saved = repo.save(menuItem);

        MenuItemResponseDto response = new MenuItemResponseDto();
        response.setItemId(saved.getItemId());
        response.setItmeName(saved.getItemName());
        response.setPrice(saved.getPrice());
        response.setAvailability(saved.isAvailability());
        return response;
    }

    @Override
    public List<MenuItemResponseDto> getAllMenuItems(){
        List<MenuItemResponseDto> responseList = new ArrayList<>();
        List<MenuItem> menuItems = repo.findAll();
        for(MenuItem m: menuItems){
            MenuItemResponseDto response = new MenuItemResponseDto();
            response.setItemId(m.getItemId());
            response.setItmeName(m.getItemName());
            response.setPrice(m.getPrice());
            response.setAvailability(m.isAvailability());
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public MenuItemResponseDto getMenuItemsById(int itemId){
        MenuItem menuItem = repo.findById(itemId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Menu Item is not found"));
        MenuItemResponseDto response = new MenuItemResponseDto();
        response.setItemId(menuItem.getItemId());
        response.setItmeName(menuItem.getItemName());
        response.setPrice(menuItem.getPrice());
        response.setAvailability(menuItem.isAvailability());
        return response;
    }

    @Override
    public MenuItemResponseDto updateMenuItem(int itemId,MenuItemRequestDto m){
        MenuItem existing = repo.findById(itemId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item not found"));
        existing.setItemName(m.getItmeName());
        existing.setPrice(m.getPrice());
        existing.setAvailability(m.isAvailability());
        MenuItem saved = repo.save(existing);

        MenuItemResponseDto respone = new MenuItemResponseDto();
        respone.setItemId(saved.getItemId());
        respone.setItmeName(saved.getItemName());
        respone.setPrice(saved.getPrice());
        respone.setAvailability(saved.isAvailability());
        return respone;
    }

    @Override
    public void deleteMenuItem(int itemId){
        repo.deleteById(itemId);
    }
}
