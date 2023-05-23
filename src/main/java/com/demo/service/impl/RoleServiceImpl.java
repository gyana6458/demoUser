package com.demo.service.impl;

import com.demo.dto.RoleDto;
import com.demo.repository.RoleRepository;
import com.demo.service.RoleService;
import entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        Role savedRole = roleRepository.save(role);
        return modelMapper.map(savedRole, RoleDto.class);
    }

    @Override
    public RoleDto getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public RoleDto updateRole(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));

        role.setName(roleDto.getName());

        Role updatedRole = roleRepository.save(role);
        return modelMapper.map(updatedRole, RoleDto.class);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream().map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
    }

}