package com.demo.controller;

import com.demo.dto.RoleDto;
import com.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    //http://localhost:8080/api/roles
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDto) {
        RoleDto createdRole = roleService.createRole(roleDto);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/roles/1
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
        RoleDto role = roleService.getRoleById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    //http://localhost:8080/api/roles/1
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable("id") Long id, @Valid @RequestBody RoleDto roleDto) {
        RoleDto updatedRole = roleService.updateRole(id, roleDto);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }
    //http://localhost:8080/api/roles/1
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>("DELETE Sucessfully",HttpStatus.NO_CONTENT);
    }
    //http://localhost:8080/api/roles
    @GetMapping("")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

}
