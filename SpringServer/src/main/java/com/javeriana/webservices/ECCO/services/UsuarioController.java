/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javeriana.webservices.ECCO.services;

import com.javeriana.webservices.ECCO.Model.Cliente;
import com.javeriana.webservices.ECCO.Model.Proveedor;
import com.javeriana.webservices.ECCO.Model.Servicio;
import com.javeriana.webservices.ECCO.pojo.ClientePojo;
import com.javeriana.webservices.ECCO.pojo.ProveedorPojo;
import com.javeriana.webservices.ECCO.repositories.ClienteRepository;
import com.javeriana.webservices.ECCO.repositories.ProveedorRepository;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author randy
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping("/cliente")
    public List<Cliente> getAllCliente() {
        return clienteRepository.findAll();
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity getClienteById(@PathVariable(value = "id") Long clienteId){
        
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if(cliente.isPresent()){
            JSONObject x = new JSONObject(cliente.get().toJsonString());
            
            return ResponseEntity.ok().body(x.toMap());
        }
        return (ResponseEntity) ResponseEntity.notFound();
        
    }
    
    @PostMapping("/cliente")
    public ResponseEntity createCliente(@Valid @RequestBody ClientePojo clientePojo) {
        try {
            Cliente cliente = new Cliente();
            cliente.setNombre(clientePojo.getNombre());
            cliente.setEdad(clientePojo.getEdad());
            cliente.setNombreUsuario(clientePojo.getNombreUsuario());
            cliente.setFoto(Base64.getDecoder().decode(clientePojo.getFoto()));
            cliente.setTelefono(clientePojo.getTelefono());
            cliente.setDescripcion(clientePojo.getDescripcion());
            cliente.setContrasena(clientePojo.getContrasena());
            Cliente x =clienteRepository.save(cliente);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.ok().body(false);
        }
        
        
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity updateCliente(@PathVariable(value = "id") Long clienteId, @Valid @RequestBody ClientePojo clienteDetails) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if(cliente.isPresent()){
            cliente.get().setNombre(clienteDetails.getNombre());
            cliente.get().setDescripcion(clienteDetails.getDescripcion());
            cliente.get().setEdad(clienteDetails.getEdad());
            cliente.get().setNombreUsuario(clienteDetails.getNombreUsuario());
            cliente.get().setTelefono(clienteDetails.getTelefono());
            cliente.get().setFoto(Base64.getDecoder().decode(clienteDetails.getFoto()));
            cliente.get().setContrasena(clienteDetails.getContrasena());
            final Cliente updatedCliente = clienteRepository.save(cliente.get());
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }
        
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity deleteCliente(@PathVariable(value = "id") Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
       
        if(cliente.isPresent()){
            clienteRepository.delete(cliente.get());
            return ResponseEntity.ok(true);
        }
        
        return (ResponseEntity) ResponseEntity.badRequest();
    }
    
    @GetMapping("/proveedor")
    public ResponseEntity getAllProveedor() {
        List<Proveedor> x = proveedorRepository.findAll();
        System.out.println(x.size());
        JSONArray res = new JSONArray();
        for(Proveedor p : x){
            res.put(new JSONObject(p.toJsonString()));
        }
        return ResponseEntity.ok().body(res.toList());
        
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity getProveedorById(@PathVariable(value = "id") Long proveedorId){
        
        Optional<Proveedor> proveedor = proveedorRepository.findById(proveedorId);
        if(proveedor.isPresent()){
            JSONObject x = new JSONObject(proveedor.get().toJsonString());
            return ResponseEntity.ok().body(x.toMap());
        }
        return (ResponseEntity) ResponseEntity.notFound();
        
    }
    
    @PostMapping("/proveedor")
    public ResponseEntity createProveedor(@Valid @RequestBody ProveedorPojo proveedorPojo) {
        try {
            Proveedor proveedor = new Proveedor();
            proveedor.setNombre(proveedorPojo.getNombre());
            proveedor.setEdad(proveedorPojo.getEdad());
            proveedor.setNombreUsuario(proveedorPojo.getNombreUsuario());
            proveedor.setFoto(Base64.getDecoder().decode(proveedorPojo.getFoto()));
            proveedor.setTelefono(proveedorPojo.getTelefono());
            proveedor.setDescripcion(proveedorPojo.getDescripcion());
            proveedor.setContrasena(proveedorPojo.getContrasena());
            Proveedor x =proveedorRepository.save(proveedor);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.ok().body(false);
        }
        
        
    }

    @PutMapping("/proveedor/{id}")
    public ResponseEntity updateProveedor(@PathVariable(value = "id") Long proveedorId, @Valid @RequestBody ProveedorPojo proveedorDetails) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(proveedorId);
        if(proveedor.isPresent()){
            proveedor.get().setNombre(proveedorDetails.getNombre());
            proveedor.get().setDescripcion(proveedorDetails.getDescripcion());
            proveedor.get().setEdad(proveedorDetails.getEdad());
            proveedor.get().setNombreUsuario(proveedorDetails.getNombreUsuario());
            proveedor.get().setTelefono(proveedorDetails.getTelefono());
            proveedor.get().setPaginaWeb(proveedorDetails.getPaginaWeb());
            proveedor.get().setContactoRS(proveedorDetails.getContactoRS());
            proveedor.get().setFoto(Base64.getDecoder().decode(proveedorDetails.getFoto()));
            final Proveedor updatedproveedor = proveedorRepository.save(proveedor.get());
            return ResponseEntity.ok(true);
        }else{
            return (ResponseEntity) ResponseEntity.badRequest();
        }
        
    }

    @DeleteMapping("/proveedor/{id}")
    public ResponseEntity deleteProveedor(@PathVariable(value = "id") Long proveedorId) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(proveedorId);
       
        if(proveedor.isPresent()){
            proveedorRepository.delete(proveedor.get());
            return ResponseEntity.ok(true);
        }
        
        return (ResponseEntity) ResponseEntity.badRequest();
    }
    
    @GetMapping("/proveedor/{id}/services")
    public ResponseEntity getServiciosProveedor(@PathVariable(value = "id") Long proveedorId){
        JSONArray res = new JSONArray();
        Optional<Proveedor> proveedor = this.proveedorRepository.findById(proveedorId);
        if(proveedor.isPresent()){
            List<Servicio> x =proveedor.get().getServicios();
            for (Servicio aux : x){
                res.put(new JSONObject(aux.toJsonString()));
            }
        }
        
        return ResponseEntity.ok(res.toList());
    }
    @PostMapping("/login")
    public ResponseEntity logIn(@Valid @RequestBody String body ){
        JSONObject credentials = new JSONObject(body);
        JSONObject response = new JSONObject();
        Cliente cliente = clienteRepository.searchByUsername(String.valueOf(credentials.get("nombreUsuario")));
        Proveedor proveedor = proveedorRepository.searchByUsername(String.valueOf(credentials.get("nombreUsuario")));
        if(cliente == null){
            if(proveedor == null){
                response.put("message", "Credenciales invalidas");
                return ResponseEntity.ok(response.toMap());
            }else{
                if(credentials.get("contrasena").equals(proveedor.getContrasena())){
                    response.put("message", "inicio de sesion completo");
                    response.put("idUsuario",proveedor.getId());
                    response.put("tipo", "proveedor");
                    return ResponseEntity.ok(response.toMap());   
                }else{
                    response.put("message", "Credenciales invalidas");
                    return ResponseEntity.ok(response.toMap());
                }
                
            }
        }else{
            if(credentials.get("contrasena").equals(cliente.getContrasena())){
                response.put("message", "inicio de sesion completo");
                response.put("idUsuario",cliente.getId());
                response.put("tipo", "cliente");
                return ResponseEntity.ok(response.toMap());   
            }else{
                response.put("message", "Credenciales invalidas");
                return ResponseEntity.ok(response.toMap());
            }
        }
        
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    
}
