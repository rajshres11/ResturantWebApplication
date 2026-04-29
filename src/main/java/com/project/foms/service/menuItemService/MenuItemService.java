package com.project.foms.service.menuItemService;

import java.util.List;

import com.project.foms.dto.menuItemdto.MenuItemRequestDto;
import com.project.foms.dto.menuItemdto.MenuItemResponseDto;

public interface MenuItemService {
    
    public MenuItemResponseDto createMenuItem(MenuItemRequestDto m);
    public List<MenuItemResponseDto> getAllMenuItems();
    public MenuItemResponseDto getMenuItemsById(int itemId);
    public MenuItemResponseDto updateMenuItem(int itemId,MenuItemRequestDto m);
    public void deleteMenuItem(int itemId);
}
