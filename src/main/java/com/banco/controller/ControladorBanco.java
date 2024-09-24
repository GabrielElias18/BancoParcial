package com.banco.controller;

import com.banco.model.Cuenta;
import com.banco.model.Usuario;
import com.banco.services.ServicioBanco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ControladorBanco {
    @Autowired
    private ServicioBanco servicioBanco;

    @GetMapping("/usuario/saldo")
    public String mostrarSaldoUsuario(Model modelo, Principal principal) {
        Cuenta cuenta = servicioBanco.obtenerCuentaPorTitular(principal.getName());
        modelo.addAttribute("saldoDisponible", cuenta.getSaldoDisponible());
        modelo.addAttribute("titularCuenta", cuenta.getTitularCuenta());

        return "saldo";
    }

    @GetMapping("/usuario/transferir")
    public String mostrarFormularioTransferencia(Model modelo) {
        return "transferencia"; // Nombre del archivo Thymeleaf
    }

    @PostMapping("/usuario/transferir")
    public String realizarTransferencia(@RequestParam String cuentaDestino, 
                                         @RequestParam double monto, 
                                         Principal principal, 
                                         Model modelo) {
        // Verificar el rol del usuario
        Usuario usuario = servicioBanco.obtenerUsuarioPorNombre(principal.getName());
        if (!usuario.getRolUsuario().equals("USUARIO")) {
            modelo.addAttribute("mensaje", "Acceso denegado. Solo los usuarios pueden realizar transferencias.");
            return "resultadoTransferencia"; // Página de resultado
        }

        String cuentaOrigen = servicioBanco.obtenerCuentaPorTitular(principal.getName()).getNumeroCuenta();
        boolean exito = servicioBanco.transferir(cuentaOrigen, cuentaDestino, monto);
        
        if (exito) {
            modelo.addAttribute("mensaje", "Transferencia realizada con éxito.");
        } else {
            modelo.addAttribute("mensaje", "Error en la transferencia. Verifica los datos.");
        }
        
        return "resultadoTransferencia"; // Página de resultado
    }

    @GetMapping("/admin/saldo")
    public String mostrarSaldoAdmin(Model modelo1, Principal principal1) {
        Cuenta cuenta = servicioBanco.obtenerCuentaPorTitular(principal1.getName());
        modelo1.addAttribute("saldoDisponible", cuenta.getSaldoDisponible());
        modelo1.addAttribute("titularCuenta", cuenta.getTitularCuenta());
        return "saldo";
    }

    @GetMapping("/admin/cuentas")
    public String listarCuentas(Model modelo) {
        modelo.addAttribute("listaCuentas", servicioBanco.obtenerTodasLasCuentas());
        return "listaCuentas";
    }
}
