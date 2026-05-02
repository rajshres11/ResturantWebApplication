package com.project.foms.service.resturantService;

import java.util.List;

import com.project.foms.dto.menuItemdto.MenuItemResponseDto;
import com.project.foms.dto.resturantdto.ResturantRequestDto;
import com.project.foms.dto.resturantdto.ResturantResponseDto;

public interface ResturantService {

    public ResturantResponseDto createResturant(ResturantRequestDto r);

    public List<ResturantResponseDto> getAllResturant();

    public ResturantResponseDto getResturantById(int resturantId);

    public ResturantResponseDto updateResturant(int resturantId, ResturantRequestDto r);

    public void deleteResturant(int resturantId);

    public List<ResturantResponseDto> getResturantByLocation(String location);

    public ResturantResponseDto getResturantByName(String resturantName);

    public List<MenuItemResponseDto> getMenuItemsByResturant(int resturantId);

    public List<ResturantResponseDto> getResturantByPage(int page, int size);

}
