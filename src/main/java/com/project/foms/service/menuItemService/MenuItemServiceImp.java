package com.project.foms.service.menuItemService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
