package catalogo.reportes.core.afiliados.afiliadosServices.implementations;

import catalogo.reportes.ReportesConfiguration;
import catalogo.reportes.core.afiliados.afiliadosServices.interfaces.IAfiliadosEmpresaService;
import catalogo.reportes.core.afiliados.afiliadosServices.interfaces.IAfiliadosSincronizadorService;
import catalogo.reportes.core.afiliados.afiliadosServices.interfaces.IAfiliadosUbicacionService;
import catalogo.reportes.core.catalogo.services.interfaces.ICatalogoSincronizadorService;
import catalogo.reportes.core.pedidos.services.interfaces.IPedidosSincronizadorService;
import catalogo.reportes.core.services.interfaces.ISincronizadorService;
import common.rondanet.clasico.core.afiliados.afiliadosModels.Empresa;
import common.rondanet.clasico.core.afiliados.afiliadosModels.Ubicacion;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AfiliadosSincronizadorService implements IAfiliadosSincronizadorService {

	Logger logger = LogManager.getLogger(AfiliadosSincronizadorService.class);

	@Autowired
	IAfiliadosEmpresaService afiliadosEmpresaService;

	@Autowired
	IAfiliadosUbicacionService afiliadosUbicacionService;

	@Autowired
	ISincronizadorService sincronizadorService;

	@Autowired
	ICatalogoSincronizadorService catalogoSincronizadorService;

	@Autowired
	IPedidosSincronizadorService pedidosSincronizadorService;

	ReportesConfiguration configuration;

	public AfiliadosSincronizadorService(ReportesConfiguration sincronizadorConfiguration) {
		this.configuration = sincronizadorConfiguration;
	}

	public boolean sincronizar(Date FechaDeActualizacion){
		boolean ocurrioAlgunErrorSincronizarEmpresas = true;
		boolean ocurrioAlgunErrorSincronizarUbicaciones = true;
		String token = sincronizadorService.login();
		if(!token.equals("")) {
			ocurrioAlgunErrorSincronizarEmpresas = sincronizarEmpresas(FechaDeActualizacion, token);
			ocurrioAlgunErrorSincronizarUbicaciones = sincronizarUbicaciones(FechaDeActualizacion, token);
		}
		return ocurrioAlgunErrorSincronizarEmpresas && ocurrioAlgunErrorSincronizarUbicaciones;
	}

	public boolean sincronizarEmpresas(Date FechaDeActualizacion, String token){
		int rows = 0;
		boolean ocurrioAlgunErrorAlEnviarAlCatalogo = false;
		boolean ocurrioAlgunErrorAlEnviarAPedidos = false;
		boolean todasLasEmpresas = false;
		while(!todasLasEmpresas) {
			int to = rows + 100;
			List<Empresa> empresas = afiliadosEmpresaService.GetAll(FechaDeActualizacion, rows + 1, to);
			rows = to;
			catalogoSincronizadorService.sincronizarEmpresas(empresas);
			pedidosSincronizadorService.sincronizarDatosAfiliados(empresas);
			if (empresas.size() < 100) {
				todasLasEmpresas = true;
			}
		}
		logger.log(Level.INFO,"Sincronizadas las Empresas");
		return ocurrioAlgunErrorAlEnviarAlCatalogo && ocurrioAlgunErrorAlEnviarAPedidos;
	}

	public boolean sincronizarUbicaciones(Date FechaDeActualizacion, String token){
		int rows = 0;
		boolean ocurrioAlgunErrorAlEnviarAlCatalogo = false;
		boolean todasLasUbicaciones = false;
		while(!todasLasUbicaciones) {
			int to = rows + 100;
			List<Ubicacion> ubicaciones = afiliadosUbicacionService.GetAll(FechaDeActualizacion, rows + 1, to);
			rows = to;
			catalogoSincronizadorService.sincronizarUbicaciones(ubicaciones);
			if (ubicaciones.size() < 100) {
				todasLasUbicaciones = true;
			}
		}
		logger.log(Level.INFO,"Sincronizadas las Ubicaciones");
		return ocurrioAlgunErrorAlEnviarAlCatalogo;
	}

}
