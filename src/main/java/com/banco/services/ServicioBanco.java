package com.banco.services;

import com.banco.model.Cuenta;
import com.banco.model.Transaccion;
import com.banco.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioBanco {
    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    public ServicioBanco() {
        // Agregando usuarios
        usuarios.add(new Usuario("admin", "admin123", "ADMINISTRADOR"));
        usuarios.add(new Usuario("cliente1", "cliente123", "USUARIO"));
        usuarios.add(new Usuario("cliente2", "cliente456", "USUARIO"));
        usuarios.add(new Usuario("cliente3", "cliente789", "USUARIO"));

        // Agregando cuentas
        cuentas.add(new Cuenta("1234", 1500.00, "cliente1"));
        cuentas.add(new Cuenta("12345", 3500.00, "admin"));
        cuentas.add(new Cuenta("6789", 2500.00, "cliente2")); // Cuenta para el cliente 2
        cuentas.add(new Cuenta("67890", 5000.00, "cliente3")); // Cuenta para el cliente 3
    }

    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentas;
    }

    public Cuenta obtenerCuentaPorTitular(String titular) {
        return cuentas.stream()
                       .filter(cuenta -> cuenta.getTitularCuenta().equals(titular))
                       .findFirst()
                       .orElse(null);
    }

    public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
        return usuarios.stream()
                       .filter(usuario -> usuario.getNombreUsuario().equals(nombreUsuario))
                       .findFirst()
                       .orElse(null);
    }

    public boolean transferir(String cuentaOrigen, String cuentaDestino, double monto) {
        Cuenta origen = obtenerCuentaPorTitular(cuentaOrigen);
        Cuenta destino = obtenerCuentaPorTitular(cuentaDestino);

        if (origen == null || destino == null || origen.getSaldoDisponible() < monto) {
            return false; 
        }

        origen.setSaldoDisponible(origen.getSaldoDisponible() - monto);
        destino.setSaldoDisponible(destino.getSaldoDisponible() + monto);

        
        Transaccion transaccion = new Transaccion(cuentaOrigen, cuentaDestino, monto);
        

        return true; 
    }
}
