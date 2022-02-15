package catalogo.reportes.core.pedidos.services.interfaces;

import common.rondanet.clasico.core.afiliados.afiliadosModels.Empresa;

import java.util.List;

public interface IPedidosSincronizadorService {
    public void sincronizarDatosAfiliados(List<Empresa> empresasASincronizar);
}
