package com.project.foms.service.resturantService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.foms.dto.menuItemdto.MenuItemResponseDto;
import com.project.foms.dto.resturantdto.ResturantRequestDto;
import com.project.foms.dto.resturantdto.ResturantResponseDto;
import com.project.foms.entity.MenuItem;
import com.project.foms.entity.Resturant;
import com.project.foms.repository.ResturantRepository;

@Service
public class ResturantServiceImp implements ResturantService{
    
    private final ResturantRepository repo;
    public ResturantServiceImp(ResturantRepository repo){
        this.repo=repo;
    }

    @Override
    public ResturantResponseDto createResturant(ResturantRequestDto r){
        Resturant resturant = new Resturant();
        resturant.setResturantName(r.getResturnatName());
        resturant.setLocation(r.getLocation());
        Resturant saved = repo.save(resturant);

        ResturantResponseDto response = new ResturantResponseDto();
        response.setResturantId(saved.getResturantId());
        response.setResturantName(saved.getResturantName());
        response.setLocation(saved.getLocation());
        return response;
    }

    @Override
    public List<ResturantResponseDto> getAllResturant(){
        List<Resturant> resturants = repo.findAll();
        List<ResturantResponseDto> responseList = new ArrayList<>();
        for(Resturant r:resturants){
            ResturantResponseDto resturant = new ResturantResponseDto();
            resturant.setResturantId(r.getResturantId());
            resturant.setResturantName(r.getResturantName());
            resturant.setLocation(r.getLocation());
            responseList.add(resturant);
        }
        return responseList;
    }

    @Override
    public ResturantResponseDto getResturantById(int resturantId){
        Resturant resturant = repo.findById(resturantId)
        // .orElseThrow(()-> new RuntimeException("No resturant found"));
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No resturnat found"));// new way to write exception no need to handle exception seperately.
        ResturantResponseDto response = new ResturantResponseDto();
        response.setResturantId(resturant.getResturantId());
        response.setResturantName(resturant.getResturantName());
        response.setLocation(resturant.getLocation());
        return response;
    }

    @Override
    public ResturantResponseDto updateResturant(int resturantId,ResturantRequestDto r){
        Resturant existing = repo.findById(resturantId)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No resturant found"));
        existing.setResturantName(r.getResturnatName());
        existing.setLocation(r.getLocation());
        Resturant saved = repo.save(existing);

        ResturantResponseDto response = new ResturantResponseDto();
        response.setResturantId(saved.getResturantId());
        response.setResturantName(saved.getResturantName());
        response.setLocation(saved.getLocation());
        return response;
    }

    @Override
    public void deleteResturant(int resturantId){
        repo.deleteById(resturantId);
    }

    @Override
    public List<ResturantResponseDto> getResturantByLocation(String location){
        List<Resturant> resturants = repo.findByLocation(location);
        List<ResturantResponseDto> responseList = new ArrayList<>();
        for(Resturant r:resturants){
            ResturantResponseDto resturant = new ResturantResponseDto();
            resturant.setResturantId(r.getResturantId());
            resturant.setResturantName(r.getResturantName());
            resturant.setLocation(r.getLocation());
            responseList.add(resturant);
        }
        return responseList;
    }

    @Override
    public ResturantResponseDto getResturantByName(String resturantName){
        Resturant resturant = repo.findByResturantName(resturantName)
         .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No resturant found"));
        ResturantResponseDto response = new ResturantResponseDto();
        response.setResturantId(resturant.getResturantId());
        response.setResturantName(resturant.getResturantName());
        response.setLocation(resturant.getLocation());
        return response;
    }

    @Override
    public List<MenuItemResponseDto> getMenuItemsByResturant(int resturantId){
        List<MenuItemResponseDto> responseList = new ArrayList<>();
        Resturant r = repo.findById(resturantId).
        orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No resturant found"));
        List<MenuItem> menuItems = r.getMenuItems();
        for(MenuItem m : menuItems){
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
    public List<ResturantResponseDto> getResturantByPage(int page,int size){
        List<ResturantResponseDto> responseList = new ArrayList<>();
        Page<Resturant> resturants = repo.findAll(PageRequest.of(page, size,Sort.by("resturantName").ascending()));

        for(Resturant r: resturants.getContent()){
            ResturantResponseDto response = new ResturantResponseDto();
            response.setResturantId(r.getResturantId());
            response.setResturantName(r.getResturantName());
            response.setLocation(r.getLocation());
            responseList.add(response);
        }
        return responseList;
    }

}
