package catalogo.reportes.core.catalogo.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(hidden = true)
public class AtributosExtendidosAlimentos extends Entidad {

    private String demoninacionLegal;

    private String nombreFantasia;

    private String contenidoEscurridoCantidad;

    private String contenidoEscurridoUnidad;

    private String registroBromatologicoAlimentarioNombre;

    private String registroBromatologicoAlimentarioNumero;

    private String otrosRegistros1Nombre;

    private String otrosRegistros1Numero;

    private String otrosRegistros2Nombre;

    private String otrosRegistros2Numero;

    private String otrosRegistros3Nombre;

    private String otrosRegistros3Numero;

    private String otrosRegistros4Nombre;

    private String otrosRegistros4Numero;

    @Schema(description = "Ingredintes")
    private String listaDeIngredientes;

    private String alergenosIntolerancias1Contiene;

    private String alergenosIntolerancias1Seleccion;

    private String alergenosIntolerancias2Contiene;

    private String alergenosIntolerancias2Seleccion;

    private String alergenosIntolerancias3Contiene;

    private String alergenosIntolerancias3Seleccion;

    private String alergenosIntolerancias4Contiene;

    private String alergenosIntolerancias4Seleccion;

    private String alergenosIntolerancias5Contiene;

    private String alergenosIntolerancias5Seleccion;

    private String alergenosIntolerancias6Contiene;

    private String alergenosIntolerancias6Seleccion;

    private String alergenosIntolerancias7Contiene;

    private String alergenosIntolerancias7Seleccion;

    private String alergenosIntolerancias8Contiene;

    private String alergenosIntolerancias8Seleccion;

    private String alergenosIntolerancias9Contiene;

    private String alergenosIntolerancias9Seleccion;

    private String alergenosIntolerancias10Contiene;

    private String alergenosIntolerancias10Seleccion;

    private String alergenosIntolerancias11Contiene;

    private String alergenosIntolerancias11Seleccion;

    private String alergenosIntolerancias12Contiene;

    private String alergenosIntolerancias12Seleccion;

    private String alergenosIntolerancias13Contiene;

    private String alergenosIntolerancias13Seleccion;

    private String alergenosIntolerancias14Contiene;

    private String alergenosIntolerancias14Seleccion;

    private String alergenosIntolerancias15Contiene;

    private String alergenosIntolerancias15Seleccion;

    private String alergenosIntolerancias16Contiene;

    private String alergenosIntolerancias16Seleccion;

    private String alergenosIntolerancias17Contiene;

    private String alergenosIntolerancias17Seleccion;

    private String alergenosIntolerancias18Contiene;

    private String alergenosIntolerancias18Seleccion;

    // todo: revisar descripción
    @Schema(description = "Indica si el alimento contiene alerta sobre exceso de grasas")
    private Boolean alertasOctogonosExcesoDeGrasas;

    @Schema(description = "Indica si el alimento contiene alerta sobre exceso de grasas saturadas")
    private Boolean alertasOctogonosExcesoDeGrasasSaturadas;

    @Schema(description = "Indica si el alimento contiene alerta sobre exceso de azúcar")
    private Boolean alertasOctogonosExcesoDeAzucar;

    @Schema(description = "Indica si el alimento contiene alerta sobre exceso de sodio")
    private Boolean alertasOctogonosExcesoDeSodio;

    @Schema(description = "Medida de la porción por la cantidad")    // todo: revisar descripción
    private String medidaDeLaPorcionCantidad;

    @Schema(description = "Medida de la porción por la unidad")    // todo: revisar descripción
    private String medidaDeLaPorcionUnidad;

    @Schema(description = "Medida de la porción casera por cantidad")    // todo: revisar descripción
    private String medidaDeLaPorcionCaseraCantidad;

    @Schema(description = "Medida de la porción casera por unidad")    // todo: revisar descripción
    private String medidaDeLaPorcionCaseraUnidad;

    @Schema(description = "Valor energético en Kilocalorías")
    private String valorEnergeticoKCal;

    @Schema(description = "Valor energético en Kilojoules")
    private String valorEnergeticoKJ;

    @Schema(description = "Qué es?")    // todo: revisar descripción
    private String valorEnergeticoKVd;

    @Schema(description = "Gramaje de proteínas")    // todo: revisar descripción
    private String proteinasGr;

    @Schema(description = "Qué es?")    // todo: revisar descripción
    private String proteinasVd;

    @Schema(description = "Gramos de carbohidratos")
    private String carbohidratosGr;

    @Schema(description = "Qué es?")    // todo: revisar descripción
    private String carbohidratosVd;

    @Schema(description = "Gramos de azúcar")
    private String azucaresGr;

    @Schema(description = "Gramos de polialcoholes")
    private String polialcoholesGr;

    @Schema(description = "Gramos de de almidón")
    private String almidonGr;

    @Schema(description = "Gramos de otros carbohidratos")
    private String otrosCarbohidratosGr;

    @Schema(description = "Gramos de grasas totales")
    private String grasasTotalesGr;

    @Schema(description = "Qué es?")    // todo: revisar descripción
    private String grasasTotalesVd;

    @Schema(description = "Gramos de grasas saturadas")
    private String grasasSaturadasGr;

    @Schema(description = "Qué es?")    // todo: revisar descripción
    private String grasasSaturadasVd;

    @Schema(description = "Gramos de grasas trans")
    private String grasasTransGr;

    @Schema(description = "Gramos de grasas monoinsaturadas")
    private String grasasMonoinsaturadasGr;

    @Schema(description = "Gramos de grasas poliinsaturadas")
    private String grasasPoliinsaturadasGr;

    @Schema(description = "Miligramos Gramos de colesterol")
    private String colesterolMg;

    @Schema(description = "Gramos de fibra alimentaria")
    private String fibraAlimentariaGr;

    @Schema(description = "%VD de fibra alimentaria")
    private String fibraAlimentariaVd;

    @Schema(description = "Gramos de Sodio")
    private String sodioMg;

    @Schema(description = "%VD Gramos de Sodio")
    private String sodioVd;

    @Schema(description = "Microgramos de vitamina A")
    private String vitaminaAUg;

    private String vitaminaAVd;

    @Schema(description = "Microgramos de vitamina D")
    private String vitaminaDUg;

    private String vitaminaDVd;

    @Schema(description = "Microgramos de vitamina C")
    private String vitaminaCUg;

    private String vitaminaCVd;

    @Schema(description = "Microgramos de vitamina E")
    private String vitaminaEUg;

    private String vitaminaEVd;

    @Schema(description = "Miligramos de Tiamina")
    private String tiaminaMg;

    private String tiaminaVd;

    @Schema(description = "Miligramos de Riboflavina")
    private String riboflavinaMg;

    private String riboflavinaVd;

    @Schema(description = "Miligramos de Niacina")
    private String niacinaMg;

    private String niacinaVd;

    @Schema(description = "Microgramos de vitamina B6")
    private String vitaminaB6Ug;

    private String vitaminaB6Vd;

    @Schema(description = "Microgramos de Ácido Fólico")
    private String acidoFolicoUg;

    private String acidoFolicoVd;

    @Schema(description = "Microgramos de vitamina B12")
    private String vitaminaB12Ug;

    private String vitaminaB12Vd;

    @Schema(description = "Microgramos de Biotina")
    private String biotinaUg;

    private String biotinaVd;

    @Schema(description = "Microgramos de Ácido Pantoténico")
    private String acidoPantotenicoUg;

    private String acidoPantotenicoVd;

    @Schema(description = "Miligramos de Calcio")
    private String calcioMg;

    private String calcioVd;

    @Schema(description = "Miligramos de Hierro")
    private String hierroMg;

    private String hierroVd;

    @Schema(description = "Miligramos de Magnesio")
    private String magnesioMg;

    private String magnesioVd;

    @Schema(description = "Miligramos de Zinc")
    private String zincMg;

    private String zincVd;

    @Schema(description = "Miligramos de Yodo")
    private String yodoMg;

    private String yodoVd;

    @Schema(description = "Microgramos de vitamina K")
    private String vitaminaKUg;

    private String vitaminaKVd;

    @Schema(description = "Miligramos de Fósforo")
    private String fosforoMg;

    private String fosforoVd;

    @Schema(description = "Miligramos de Flour")
    private String fluorMg;

    private String fluorVd;

    @Schema(description = "Microgramos de Cobre")
    private String cobreUg;

    private String cobreVd;

    @Schema(description = "Microgramos de Selenio")
    private String selenioUg;

    private String selenioVd;

    @Schema(description = "Microgramos de Molibdeno")
    private String molibdenoUg;

    private String molibdenoVd;

    @Schema(description = "Microgramos de Cromo")
    private String cromoUg;

    private String cromoVd;

    @Schema(description = "Microgramos de Magneso")
    private String manganesoUg;

    private String manganesoVd;

    @Schema(description = "Microgramos de Colina")
    private String colinaUg;

    private String colinaVd;

    @Schema(description = "Microgramos de Colina")
    private String potasioMg;

    private String clorurosMg;

    private String informacionNutricionalComplementaria;

    private String informacionNutricionalAdicional;

    @Schema(description = "Qué es?")    // todo: revisar descripción
    private String instruccionesDeUso; // todo: varible escrita incorrectamente

    @Schema(description = "Qué es?")    // todo: revisar descripción
    private String declaracionSimplificadaNoAportaCantidades; // todo: varible escrita incorrectamente

    @Schema(description = "Qué es?")    // todo: revisar descripción
    private String declaracionSimplificadaNotas;

    @Schema(description = "Nombre del elaborador del alimento")
    private String elaboradorNombre;

    @Schema(description = "Dirección del elaborador del alimento")
    private String elaboradorDireccion;

    @Schema(description = "Nombre del distribuidor del alimento")
    private String distribuidorNombre;

    @Schema(description = "Dirección del distribuidor del alimento")
    private String distribuidorDireccion;

    @Schema(description = "Nombre del importador del alimento")
    private String importadorNombre;

    @Schema(description = "Dirección del importador del alimento")
    private String importadorDireccion;

    private String condicionesDeAlmacenamientoEnvaseCerrado;

    private String condicionesDeAlmacenamientoEnvaseAbierto;

    private Boolean contieneOmg;

    private String servicioDeAtencionAlConsumidor;

    private Boolean aplicaDecretoBebidasAlcoholicas;

    private String rotuloNutricionalImg;

    private Boolean fueActualizadoElRotuloNutricional;

    public AtributosExtendidosAlimentos() {
    }

    public AtributosExtendidosAlimentos(
            String demoninacionLegal,
            String nombreFantasia,
            String contenidoEscurridoCantidad,
            String contenidoEscurridoUnidad,
            String registroBromatologicoAlimentarioNombre,
            String registroBromatologicoAlimentarioNumero,
            String otrosRegistros1Nombre,
            String otrosRegistros1Numero,
            String otrosRegistros2Nombre,
            String otrosRegistros2Numero,
            String otrosRegistros3Nombre,
            String otrosRegistros3Numero,
            String otrosRegistros4Nombre,
            String otrosRegistros4Numero,
            String listaDeIngredientes,
            String alergenosIntolerancias1Contiene,
            String alergenosIntolerancias1Seleccion,
            String alergenosIntolerancias2Contiene,
            String alergenosIntolerancias2Seleccion,
            String alergenosIntolerancias3Contiene,
            String alergenosIntolerancias3Seleccion,
            String alergenosIntolerancias4Contiene,
            String alergenosIntolerancias4Seleccion,
            String alergenosIntolerancias5Contiene,
            String alergenosIntolerancias5Seleccion,
            String alergenosIntolerancias6Contiene,
            String alergenosIntolerancias6Seleccion,
            String alergenosIntolerancias7Contiene,
            String alergenosIntolerancias7Seleccion,
            String alergenosIntolerancias8Contiene,
            String alergenosIntolerancias8Seleccion,
            String alergenosIntolerancias9Contiene,
            String alergenosIntolerancias9Seleccion,
            String alergenosIntolerancias10Contiene,
            String alergenosIntolerancias10Seleccion,
            String alergenosIntolerancias11Contiene,
            String alergenosIntolerancias11Seleccion,
            String alergenosIntolerancias12Contiene,
            String alergenosIntolerancias12Seleccion,
            String alergenosIntolerancias13Contiene,
            String alergenosIntolerancias13Seleccion,
            String alergenosIntolerancias14Contiene,
            String alergenosIntolerancias14Seleccion,
            String alergenosIntolerancias15Contiene,
            String alergenosIntolerancias15Seleccion,
            String alergenosIntolerancias16Contiene,
            String alergenosIntolerancias16Seleccion,
            String alergenosIntolerancias17Contiene,
            String alergenosIntolerancias17Seleccion,
            String alergenosIntolerancias18Contiene,
            String alergenosIntolerancias18Seleccion,
            Boolean alertasOctogonosExcesoDeGrasas,
            Boolean alertasOctogonosExcesoDeGrasasSaturadas,
            Boolean alertasOctogonosExcesoDeAzucar,
            Boolean alertasOctogonosExcesoDeSodio,
            String medidaDeLaPorcionCantidad,
            String medidaDeLaPorcionUnidad,
            String medidaDeLaPorcionCaseraCantidad,
            String medidaDeLaPorcionCaseraUnidad,
            String valorEnergeticoKCal,
            String valorEnergeticoKJ,
            String valorEnergeticoKVd,
            String proteinasGr,
            String proteinasVd,
            String carbohidratosGr,
            String carbohidratosVd,
            String azucaresGr,
            String polialcoholesGr,
            String almidonGr,
            String otrosCarbohidratosGr,
            String grasasTotalesGr,
            String grasasTotalesVd,
            String grasasSaturadasGr,
            String grasasSaturadasVd,
            String grasasTransGr,
            String grasasMonoinsaturadasGr,
            String grasasPoliinsaturadasGr,
            String colesterolMg,
            String fibraAlimentariaGr,
            String fibraAlimentariaVd,
            String sodioMg,
            String sodioVd,
            String vitaminaAUg,
            String vitaminaAVd,
            String vitaminaDUg,
            String vitaminaDVd,
            String vitaminaCUg,
            String vitaminaCVd,
            String vitaminaEUg,
            String vitaminaEVd,
            String tiaminaMg,
            String tiaminaVd,
            String riboflavinaMg,
            String riboflavinaVd,
            String niacinaMg,
            String niacinaVd,
            String vitaminaB6Ug,
            String vitaminaB6Vd,
            String acidoFolicoUg,
            String acidoFolicoVd,
            String vitaminaB12Ug,
            String vitaminaB12Vd,
            String biotinaUg,
            String biotinaVd,
            String acidoPantotenicoUg,
            String acidoPantotenicoVd,
            String calcioMg,
            String calcioVd,
            String hierroMg,
            String hierroVd,
            String magnesioMg,
            String magnesioVd,
            String zincMg,
            String zincVd,
            String yodoMg,
            String yodoVd,
            String vitaminaKUg,
            String vitaminaKVd,
            String fosforoMg,
            String fosforoVd,
            String fluorMg,
            String fluorVd,
            String cobreUg,
            String cobreVd,
            String selenioUg,
            String selenioVd,
            String molibdenoUg,
            String molibdenoVd,
            String cromoUg,
            String cromoVd,
            String manganesoUg,
            String manganesoVd,
            String colinaUg,
            String colinaVd,
            String potasioMg,
            String clorurosMg,
            String informacionNutricionalComplementaria,
            String informacionNutricionalAdicional,
            String instruccionesDeUso,
            String declaracionSimplificadaNoAportaCantidades,
            String declaracionSimplificadaNotas,
            String elaboradorNombre,
            String elaboradorDireccion,
            String distribuidorNombre,
            String distribuidorDireccion,
            String importadorNombre,
            String importadorDireccion,
            String condicionesDeAlmacenamientoEnvaseCerrado,
            String condicionesDeAlmacenamientoEnvaseAbierto,
            Boolean contieneOmg,
            String servicioDeAtencionAlConsumidor,
            Boolean aplicaDecretoBebidasAlcoholicas
    ) {
        this.demoninacionLegal = demoninacionLegal;
        this.nombreFantasia = nombreFantasia;
        this.contenidoEscurridoCantidad = contenidoEscurridoCantidad;
        this.contenidoEscurridoUnidad = contenidoEscurridoUnidad;
        this.registroBromatologicoAlimentarioNombre = registroBromatologicoAlimentarioNombre;
        this.registroBromatologicoAlimentarioNumero = registroBromatologicoAlimentarioNumero;
        this.otrosRegistros1Nombre = otrosRegistros1Nombre;
        this.otrosRegistros1Numero = otrosRegistros1Numero;
        this.otrosRegistros2Nombre = otrosRegistros2Nombre;
        this.otrosRegistros2Numero = otrosRegistros2Numero;
        this.otrosRegistros3Nombre = otrosRegistros3Nombre;
        this.otrosRegistros3Numero = otrosRegistros3Numero;
        this.otrosRegistros4Nombre = otrosRegistros4Nombre;
        this.otrosRegistros4Numero = otrosRegistros4Numero;
        this.listaDeIngredientes = listaDeIngredientes;
        this.alergenosIntolerancias1Contiene = alergenosIntolerancias1Contiene;
        this.alergenosIntolerancias1Seleccion = alergenosIntolerancias1Seleccion;
        this.alergenosIntolerancias2Contiene = alergenosIntolerancias2Contiene;
        this.alergenosIntolerancias2Seleccion = alergenosIntolerancias2Seleccion;
        this.alergenosIntolerancias3Contiene = alergenosIntolerancias3Contiene;
        this.alergenosIntolerancias3Seleccion = alergenosIntolerancias3Seleccion;
        this.alergenosIntolerancias4Contiene = alergenosIntolerancias4Contiene;
        this.alergenosIntolerancias4Seleccion = alergenosIntolerancias4Seleccion;
        this.alergenosIntolerancias5Contiene = alergenosIntolerancias5Contiene;
        this.alergenosIntolerancias5Seleccion = alergenosIntolerancias5Seleccion;
        this.alergenosIntolerancias6Contiene = alergenosIntolerancias6Contiene;
        this.alergenosIntolerancias6Seleccion = alergenosIntolerancias6Seleccion;
        this.alergenosIntolerancias7Contiene = alergenosIntolerancias7Contiene;
        this.alergenosIntolerancias7Seleccion = alergenosIntolerancias7Seleccion;
        this.alergenosIntolerancias8Contiene = alergenosIntolerancias8Contiene;
        this.alergenosIntolerancias8Seleccion = alergenosIntolerancias8Seleccion;
        this.alergenosIntolerancias9Contiene = alergenosIntolerancias9Contiene;
        this.alergenosIntolerancias9Seleccion = alergenosIntolerancias9Seleccion;
        this.alergenosIntolerancias10Contiene = alergenosIntolerancias10Contiene;
        this.alergenosIntolerancias10Seleccion = alergenosIntolerancias10Seleccion;
        this.alergenosIntolerancias11Contiene = alergenosIntolerancias11Contiene;
        this.alergenosIntolerancias11Seleccion = alergenosIntolerancias11Seleccion;
        this.alergenosIntolerancias12Contiene = alergenosIntolerancias12Contiene;
        this.alergenosIntolerancias12Seleccion = alergenosIntolerancias12Seleccion;
        this.alergenosIntolerancias13Contiene = alergenosIntolerancias13Contiene;
        this.alergenosIntolerancias13Seleccion = alergenosIntolerancias13Seleccion;
        this.alergenosIntolerancias14Contiene = alergenosIntolerancias14Contiene;
        this.alergenosIntolerancias14Seleccion = alergenosIntolerancias14Seleccion;
        this.alergenosIntolerancias15Contiene = alergenosIntolerancias15Contiene;
        this.alergenosIntolerancias15Seleccion = alergenosIntolerancias15Seleccion;
        this.alergenosIntolerancias16Contiene = alergenosIntolerancias16Contiene;
        this.alergenosIntolerancias16Seleccion = alergenosIntolerancias16Seleccion;
        this.alergenosIntolerancias17Contiene = alergenosIntolerancias17Contiene;
        this.alergenosIntolerancias17Seleccion = alergenosIntolerancias17Seleccion;
        this.alergenosIntolerancias18Contiene = alergenosIntolerancias18Contiene;
        this.alergenosIntolerancias18Seleccion = alergenosIntolerancias18Seleccion;
        this.alertasOctogonosExcesoDeGrasas = alertasOctogonosExcesoDeGrasas;
        this.alertasOctogonosExcesoDeGrasasSaturadas = alertasOctogonosExcesoDeGrasasSaturadas;
        this.alertasOctogonosExcesoDeAzucar = alertasOctogonosExcesoDeAzucar;
        this.alertasOctogonosExcesoDeSodio = alertasOctogonosExcesoDeSodio;
        this.medidaDeLaPorcionCantidad = medidaDeLaPorcionCantidad;
        this.medidaDeLaPorcionUnidad = medidaDeLaPorcionUnidad;
        this.medidaDeLaPorcionCaseraCantidad = medidaDeLaPorcionCaseraCantidad;
        this.medidaDeLaPorcionCaseraUnidad = medidaDeLaPorcionCaseraUnidad;
        this.valorEnergeticoKCal = valorEnergeticoKCal;
        this.valorEnergeticoKJ = valorEnergeticoKJ;
        this.valorEnergeticoKVd = valorEnergeticoKVd;
        this.proteinasGr = proteinasGr;
        this.proteinasVd = proteinasVd;
        this.carbohidratosGr = carbohidratosGr;
        this.carbohidratosVd = carbohidratosVd;
        this.azucaresGr = azucaresGr;
        this.polialcoholesGr = polialcoholesGr;
        this.almidonGr = almidonGr;
        this.otrosCarbohidratosGr = otrosCarbohidratosGr;
        this.grasasTotalesGr = grasasTotalesGr;
        this.grasasTotalesVd = grasasTotalesVd;
        this.grasasSaturadasGr = grasasSaturadasGr;
        this.grasasSaturadasVd = grasasSaturadasVd;
        this.grasasTransGr = grasasTransGr;
        this.grasasMonoinsaturadasGr = grasasMonoinsaturadasGr;
        this.grasasPoliinsaturadasGr = grasasPoliinsaturadasGr;
        this.colesterolMg = colesterolMg;
        this.fibraAlimentariaGr = fibraAlimentariaGr;
        this.fibraAlimentariaVd = fibraAlimentariaVd;
        this.sodioMg = sodioMg;
        this.sodioVd = sodioVd;
        this.vitaminaAUg = vitaminaAUg;
        this.vitaminaAVd = vitaminaAVd;
        this.vitaminaDUg = vitaminaDUg;
        this.vitaminaDVd = vitaminaDVd;
        this.vitaminaCUg = vitaminaCUg;
        this.vitaminaCVd = vitaminaCVd;
        this.vitaminaEUg = vitaminaEUg;
        this.vitaminaEVd = vitaminaEVd;
        this.tiaminaMg = tiaminaMg;
        this.tiaminaVd = tiaminaVd;
        this.riboflavinaMg = riboflavinaMg;
        this.riboflavinaVd = riboflavinaVd;
        this.niacinaMg = niacinaMg;
        this.niacinaVd = niacinaVd;
        this.vitaminaB6Ug = vitaminaB6Ug;
        this.vitaminaB6Vd = vitaminaB6Vd;
        this.acidoFolicoUg = acidoFolicoUg;
        this.acidoFolicoVd = acidoFolicoVd;
        this.vitaminaB12Ug = vitaminaB12Ug;
        this.vitaminaB12Vd = vitaminaB12Vd;
        this.biotinaUg = biotinaUg;
        this.biotinaVd = biotinaVd;
        this.acidoPantotenicoUg = acidoPantotenicoUg;
        this.acidoPantotenicoVd = acidoPantotenicoVd;
        this.calcioMg = calcioMg;
        this.calcioVd = calcioVd;
        this.hierroMg = hierroMg;
        this.hierroVd = hierroVd;
        this.magnesioMg = magnesioMg;
        this.magnesioVd = magnesioVd;
        this.zincMg = zincMg;
        this.zincVd = zincVd;
        this.yodoMg = yodoMg;
        this.yodoVd = yodoVd;
        this.vitaminaKUg = vitaminaKUg;
        this.vitaminaKVd = vitaminaKVd;
        this.fosforoMg = fosforoMg;
        this.fosforoVd = fosforoVd;
        this.fluorMg = fluorMg;
        this.fluorVd = fluorVd;
        this.cobreUg = cobreUg;
        this.cobreVd = cobreVd;
        this.selenioUg = selenioUg;
        this.selenioVd = selenioVd;
        this.molibdenoUg = molibdenoUg;
        this.molibdenoVd = molibdenoVd;
        this.cromoUg = cromoUg;
        this.cromoVd = cromoVd;
        this.manganesoUg = manganesoUg;
        this.manganesoVd = manganesoVd;
        this.colinaUg = colinaUg;
        this.colinaVd = colinaVd;
        this.potasioMg = potasioMg;
        this.clorurosMg = clorurosMg;
        this.informacionNutricionalComplementaria = informacionNutricionalComplementaria;
        this.informacionNutricionalAdicional = informacionNutricionalAdicional;
        this.instruccionesDeUso = instruccionesDeUso;
        this.declaracionSimplificadaNoAportaCantidades = declaracionSimplificadaNoAportaCantidades;
        this.declaracionSimplificadaNotas = declaracionSimplificadaNotas;
        this.elaboradorNombre = elaboradorNombre;
        this.elaboradorDireccion = elaboradorDireccion;
        this.distribuidorNombre = distribuidorNombre;
        this.distribuidorDireccion = distribuidorDireccion;
        this.importadorNombre = importadorNombre;
        this.importadorDireccion = importadorDireccion;
        this.condicionesDeAlmacenamientoEnvaseCerrado = condicionesDeAlmacenamientoEnvaseCerrado;
        this.condicionesDeAlmacenamientoEnvaseAbierto = condicionesDeAlmacenamientoEnvaseAbierto;
        this.contieneOmg = contieneOmg;
        this.servicioDeAtencionAlConsumidor = servicioDeAtencionAlConsumidor;
        this.aplicaDecretoBebidasAlcoholicas = aplicaDecretoBebidasAlcoholicas;
        this.rotuloNutricionalImg = "";
        this.fueActualizadoElRotuloNutricional = false;
    }

    public String getDemoninacionLegal() {
        return demoninacionLegal;
    }

    public void setDemoninacionLegal(String demoninacionLegal) {
        this.demoninacionLegal = demoninacionLegal;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public String getContenidoEscurridoCantidad() {
        return contenidoEscurridoCantidad;
    }

    public void setContenidoEscurridoCantidad(String contenidoEscurridoCantidad) {
        this.contenidoEscurridoCantidad = contenidoEscurridoCantidad;
    }

    public String getContenidoEscurridoUnidad() {
        return contenidoEscurridoUnidad;
    }

    public void setContenidoEscurridoUnidad(String contenidoEscurridoUnidad) {
        this.contenidoEscurridoUnidad = contenidoEscurridoUnidad;
    }

    public String getRegistroBromatologicoAlimentarioNombre() {
        return registroBromatologicoAlimentarioNombre;
    }

    public void setRegistroBromatologicoAlimentarioNombre(String registroBromatologicoAlimentarioNombre) {
        this.registroBromatologicoAlimentarioNombre = registroBromatologicoAlimentarioNombre;
    }

    public String getRegistroBromatologicoAlimentarioNumero() {
        return registroBromatologicoAlimentarioNumero;
    }

    public void setRegistroBromatologicoAlimentarioNumero(String registroBromatologicoAlimentarioNumero) {
        this.registroBromatologicoAlimentarioNumero = registroBromatologicoAlimentarioNumero;
    }

    public String getOtrosRegistros1Nombre() {
        return otrosRegistros1Nombre;
    }

    public void setOtrosRegistros1Nombre(String otrosRegistros1Nombre) {
        this.otrosRegistros1Nombre = otrosRegistros1Nombre;
    }

    public String getOtrosRegistros1Numero() {
        return otrosRegistros1Numero;
    }

    public void setOtrosRegistros1Numero(String otrosRegistros1Numero) {
        this.otrosRegistros1Numero = otrosRegistros1Numero;
    }

    public String getOtrosRegistros2Nombre() {
        return otrosRegistros2Nombre;
    }

    public void setOtrosRegistros2Nombre(String otrosRegistros2Nombre) {
        this.otrosRegistros2Nombre = otrosRegistros2Nombre;
    }

    public String getOtrosRegistros2Numero() {
        return otrosRegistros2Numero;
    }

    public void setOtrosRegistros2Numero(String otrosRegistros2Numero) {
        this.otrosRegistros2Numero = otrosRegistros2Numero;
    }

    public String getOtrosRegistros3Nombre() {
        return otrosRegistros3Nombre;
    }

    public void setOtrosRegistros3Nombre(String otrosRegistros3Nombre) {
        this.otrosRegistros3Nombre = otrosRegistros3Nombre;
    }

    public String getOtrosRegistros3Numero() {
        return otrosRegistros3Numero;
    }

    public void setOtrosRegistros3Numero(String otrosRegistros3Numero) {
        this.otrosRegistros3Numero = otrosRegistros3Numero;
    }

    public String getOtrosRegistros4Nombre() {
        return otrosRegistros4Nombre;
    }

    public void setOtrosRegistros4Nombre(String otrosRegistros4Nombre) {
        this.otrosRegistros4Nombre = otrosRegistros4Nombre;
    }

    public String getOtrosRegistros4Numero() {
        return otrosRegistros4Numero;
    }

    public void setOtrosRegistros4Numero(String otrosRegistros4Numero) {
        this.otrosRegistros4Numero = otrosRegistros4Numero;
    }

    public String getListaDeIngredientes() {
        return listaDeIngredientes;
    }

    public void setListaDeIngredientes(String listaDeIngredientes) {
        this.listaDeIngredientes = listaDeIngredientes;
    }

    public String getAlergenosIntolerancias1Contiene() {
        return alergenosIntolerancias1Contiene;
    }

    public void setAlergenosIntolerancias1Contiene(String alergenosIntolerancias1Contiene) {
        this.alergenosIntolerancias1Contiene = alergenosIntolerancias1Contiene;
    }

    public String getAlergenosIntolerancias1Seleccion() {
        return alergenosIntolerancias1Seleccion;
    }

    public void setAlergenosIntolerancias1Seleccion(String alergenosIntolerancias1Seleccion) {
        this.alergenosIntolerancias1Seleccion = alergenosIntolerancias1Seleccion;
    }

    public String getAlergenosIntolerancias2Contiene() {
        return alergenosIntolerancias2Contiene;
    }

    public void setAlergenosIntolerancias2Contiene(String alergenosIntolerancias2Contiene) {
        this.alergenosIntolerancias2Contiene = alergenosIntolerancias2Contiene;
    }

    public String getAlergenosIntolerancias2Seleccion() {
        return alergenosIntolerancias2Seleccion;
    }

    public void setAlergenosIntolerancias2Seleccion(String alergenosIntolerancias2Seleccion) {
        this.alergenosIntolerancias2Seleccion = alergenosIntolerancias2Seleccion;
    }

    public String getAlergenosIntolerancias3Contiene() {
        return alergenosIntolerancias3Contiene;
    }

    public void setAlergenosIntolerancias3Contiene(String alergenosIntolerancias3Contiene) {
        this.alergenosIntolerancias3Contiene = alergenosIntolerancias3Contiene;
    }

    public String getAlergenosIntolerancias3Seleccion() {
        return alergenosIntolerancias3Seleccion;
    }

    public void setAlergenosIntolerancias3Seleccion(String alergenosIntolerancias3Seleccion) {
        this.alergenosIntolerancias3Seleccion = alergenosIntolerancias3Seleccion;
    }

    public String getAlergenosIntolerancias4Contiene() {
        return alergenosIntolerancias4Contiene;
    }

    public void setAlergenosIntolerancias4Contiene(String alergenosIntolerancias4Contiene) {
        this.alergenosIntolerancias4Contiene = alergenosIntolerancias4Contiene;
    }

    public String getAlergenosIntolerancias4Seleccion() {
        return alergenosIntolerancias4Seleccion;
    }

    public void setAlergenosIntolerancias4Seleccion(String alergenosIntolerancias4Seleccion) {
        this.alergenosIntolerancias4Seleccion = alergenosIntolerancias4Seleccion;
    }

    public String getAlergenosIntolerancias5Contiene() {
        return alergenosIntolerancias5Contiene;
    }

    public void setAlergenosIntolerancias5Contiene(String alergenosIntolerancias5Contiene) {
        this.alergenosIntolerancias5Contiene = alergenosIntolerancias5Contiene;
    }

    public String getAlergenosIntolerancias5Seleccion() {
        return alergenosIntolerancias5Seleccion;
    }

    public void setAlergenosIntolerancias5Seleccion(String alergenosIntolerancias5Seleccion) {
        this.alergenosIntolerancias5Seleccion = alergenosIntolerancias5Seleccion;
    }

    public String getAlergenosIntolerancias6Contiene() {
        return alergenosIntolerancias6Contiene;
    }

    public void setAlergenosIntolerancias6Contiene(String alergenosIntolerancias6Contiene) {
        this.alergenosIntolerancias6Contiene = alergenosIntolerancias6Contiene;
    }

    public String getAlergenosIntolerancias6Seleccion() {
        return alergenosIntolerancias6Seleccion;
    }

    public void setAlergenosIntolerancias6Seleccion(String alergenosIntolerancias6Seleccion) {
        this.alergenosIntolerancias6Seleccion = alergenosIntolerancias6Seleccion;
    }

    public String getAlergenosIntolerancias7Contiene() {
        return alergenosIntolerancias7Contiene;
    }

    public void setAlergenosIntolerancias7Contiene(String alergenosIntolerancias7Contiene) {
        this.alergenosIntolerancias7Contiene = alergenosIntolerancias7Contiene;
    }

    public String getAlergenosIntolerancias7Seleccion() {
        return alergenosIntolerancias7Seleccion;
    }

    public void setAlergenosIntolerancias7Seleccion(String alergenosIntolerancias7Seleccion) {
        this.alergenosIntolerancias7Seleccion = alergenosIntolerancias7Seleccion;
    }

    public String getAlergenosIntolerancias8Contiene() {
        return alergenosIntolerancias8Contiene;
    }

    public void setAlergenosIntolerancias8Contiene(String alergenosIntolerancias8Contiene) {
        this.alergenosIntolerancias8Contiene = alergenosIntolerancias8Contiene;
    }

    public String getAlergenosIntolerancias8Seleccion() {
        return alergenosIntolerancias8Seleccion;
    }

    public void setAlergenosIntolerancias8Seleccion(String alergenosIntolerancias8Seleccion) {
        this.alergenosIntolerancias8Seleccion = alergenosIntolerancias8Seleccion;
    }

    public String getAlergenosIntolerancias9Contiene() {
        return alergenosIntolerancias9Contiene;
    }

    public void setAlergenosIntolerancias9Contiene(String alergenosIntolerancias9Contiene) {
        this.alergenosIntolerancias9Contiene = alergenosIntolerancias9Contiene;
    }

    public String getAlergenosIntolerancias9Seleccion() {
        return alergenosIntolerancias9Seleccion;
    }

    public void setAlergenosIntolerancias9Seleccion(String alergenosIntolerancias9Seleccion) {
        this.alergenosIntolerancias9Seleccion = alergenosIntolerancias9Seleccion;
    }

    public String getAlergenosIntolerancias10Contiene() {
        return alergenosIntolerancias10Contiene;
    }

    public void setAlergenosIntolerancias10Contiene(String alergenosIntolerancias10Contiene) {
        this.alergenosIntolerancias10Contiene = alergenosIntolerancias10Contiene;
    }

    public String getAlergenosIntolerancias10Seleccion() {
        return alergenosIntolerancias10Seleccion;
    }

    public void setAlergenosIntolerancias10Seleccion(String alergenosIntolerancias10Seleccion) {
        this.alergenosIntolerancias10Seleccion = alergenosIntolerancias10Seleccion;
    }

    public String getAlergenosIntolerancias11Contiene() {
        return alergenosIntolerancias11Contiene;
    }

    public void setAlergenosIntolerancias11Contiene(String alergenosIntolerancias11Contiene) {
        this.alergenosIntolerancias11Contiene = alergenosIntolerancias11Contiene;
    }

    public String getAlergenosIntolerancias11Seleccion() {
        return alergenosIntolerancias11Seleccion;
    }

    public void setAlergenosIntolerancias11Seleccion(String alergenosIntolerancias11Seleccion) {
        this.alergenosIntolerancias11Seleccion = alergenosIntolerancias11Seleccion;
    }

    public String getAlergenosIntolerancias12Contiene() {
        return alergenosIntolerancias12Contiene;
    }

    public void setAlergenosIntolerancias12Contiene(String alergenosIntolerancias12Contiene) {
        this.alergenosIntolerancias12Contiene = alergenosIntolerancias12Contiene;
    }

    public String getAlergenosIntolerancias12Seleccion() {
        return alergenosIntolerancias12Seleccion;
    }

    public void setAlergenosIntolerancias12Seleccion(String alergenosIntolerancias12Seleccion) {
        this.alergenosIntolerancias12Seleccion = alergenosIntolerancias12Seleccion;
    }

    public String getAlergenosIntolerancias13Contiene() {
        return alergenosIntolerancias13Contiene;
    }

    public void setAlergenosIntolerancias13Contiene(String alergenosIntolerancias13Contiene) {
        this.alergenosIntolerancias13Contiene = alergenosIntolerancias13Contiene;
    }

    public String getAlergenosIntolerancias13Seleccion() {
        return alergenosIntolerancias13Seleccion;
    }

    public void setAlergenosIntolerancias13Seleccion(String alergenosIntolerancias13Seleccion) {
        this.alergenosIntolerancias13Seleccion = alergenosIntolerancias13Seleccion;
    }

    public String getAlergenosIntolerancias14Contiene() {
        return alergenosIntolerancias14Contiene;
    }

    public void setAlergenosIntolerancias14Contiene(String alergenosIntolerancias14Contiene) {
        this.alergenosIntolerancias14Contiene = alergenosIntolerancias14Contiene;
    }

    public String getAlergenosIntolerancias14Seleccion() {
        return alergenosIntolerancias14Seleccion;
    }

    public void setAlergenosIntolerancias14Seleccion(String alergenosIntolerancias14Seleccion) {
        this.alergenosIntolerancias14Seleccion = alergenosIntolerancias14Seleccion;
    }

    public String getAlergenosIntolerancias15Contiene() {
        return alergenosIntolerancias15Contiene;
    }

    public void setAlergenosIntolerancias15Contiene(String alergenosIntolerancias15Contiene) {
        this.alergenosIntolerancias15Contiene = alergenosIntolerancias15Contiene;
    }

    public String getAlergenosIntolerancias15Seleccion() {
        return alergenosIntolerancias15Seleccion;
    }

    public void setAlergenosIntolerancias15Seleccion(String alergenosIntolerancias15Seleccion) {
        this.alergenosIntolerancias15Seleccion = alergenosIntolerancias15Seleccion;
    }

    public String getAlergenosIntolerancias16Contiene() {
        return alergenosIntolerancias16Contiene;
    }

    public void setAlergenosIntolerancias16Contiene(String alergenosIntolerancias16Contiene) {
        this.alergenosIntolerancias16Contiene = alergenosIntolerancias16Contiene;
    }

    public String getAlergenosIntolerancias16Seleccion() {
        return alergenosIntolerancias16Seleccion;
    }

    public void setAlergenosIntolerancias16Seleccion(String alergenosIntolerancias16Seleccion) {
        this.alergenosIntolerancias16Seleccion = alergenosIntolerancias16Seleccion;
    }

    public String getAlergenosIntolerancias17Contiene() {
        return alergenosIntolerancias17Contiene;
    }

    public void setAlergenosIntolerancias17Contiene(String alergenosIntolerancias17Contiene) {
        this.alergenosIntolerancias17Contiene = alergenosIntolerancias17Contiene;
    }

    public String getAlergenosIntolerancias17Seleccion() {
        return alergenosIntolerancias17Seleccion;
    }

    public void setAlergenosIntolerancias17Seleccion(String alergenosIntolerancias17Seleccion) {
        this.alergenosIntolerancias17Seleccion = alergenosIntolerancias17Seleccion;
    }

    public String getAlergenosIntolerancias18Contiene() {
        return alergenosIntolerancias18Contiene;
    }

    public void setAlergenosIntolerancias18Contiene(String alergenosIntolerancias18Contiene) {
        this.alergenosIntolerancias18Contiene = alergenosIntolerancias18Contiene;
    }

    public String getAlergenosIntolerancias18Seleccion() {
        return alergenosIntolerancias18Seleccion;
    }

    public void setAlergenosIntolerancias18Seleccion(String alergenosIntolerancias18Seleccion) {
        this.alergenosIntolerancias18Seleccion = alergenosIntolerancias18Seleccion;
    }

    public Boolean getAlertasOctogonosExcesoDeGrasas() {
        return alertasOctogonosExcesoDeGrasas;
    }

    public void setAlertasOctogonosExcesoDeGrasas(Boolean alertasOctogonosExcesoDeGrasas) {
        this.alertasOctogonosExcesoDeGrasas = alertasOctogonosExcesoDeGrasas;
    }

    public Boolean getAlertasOctogonosExcesoDeGrasasSaturadas() {
        return alertasOctogonosExcesoDeGrasasSaturadas;
    }

    public void setAlertasOctogonosExcesoDeGrasasSaturadas(Boolean alertasOctogonosExcesoDeGrasasSaturadas) {
        this.alertasOctogonosExcesoDeGrasasSaturadas = alertasOctogonosExcesoDeGrasasSaturadas;
    }

    public Boolean getAlertasOctogonosExcesoDeAzucar() {
        return alertasOctogonosExcesoDeAzucar;
    }

    public void setAlertasOctogonosExcesoDeAzucar(Boolean alertasOctogonosExcesoDeAzucar) {
        this.alertasOctogonosExcesoDeAzucar = alertasOctogonosExcesoDeAzucar;
    }

    public Boolean getAlertasOctogonosExcesoDeSodio() {
        return alertasOctogonosExcesoDeSodio;
    }

    public void setAlertasOctogonosExcesoDeSodio(Boolean alertasOctogonosExcesoDeSodio) {
        this.alertasOctogonosExcesoDeSodio = alertasOctogonosExcesoDeSodio;
    }

    public String getMedidaDeLaPorcionCantidad() {
        return medidaDeLaPorcionCantidad;
    }

    public void setMedidaDeLaPorcionCantidad(String medidaDeLaPorcionCantidad) {
        this.medidaDeLaPorcionCantidad = medidaDeLaPorcionCantidad;
    }

    public String getMedidaDeLaPorcionUnidad() {
        return medidaDeLaPorcionUnidad;
    }

    public void setMedidaDeLaPorcionUnidad(String medidaDeLaPorcionUnidad) {
        this.medidaDeLaPorcionUnidad = medidaDeLaPorcionUnidad;
    }

    public String getMedidaDeLaPorcionCaseraCantidad() {
        return medidaDeLaPorcionCaseraCantidad;
    }

    public void setMedidaDeLaPorcionCaseraCantidad(String medidaDeLaPorcionCaseraCantidad) {
        this.medidaDeLaPorcionCaseraCantidad = medidaDeLaPorcionCaseraCantidad;
    }

    public String getMedidaDeLaPorcionCaseraUnidad() {
        return medidaDeLaPorcionCaseraUnidad;
    }

    public void setMedidaDeLaPorcionCaseraUnidad(String medidaDeLaPorcionCaseraUnidad) {
        this.medidaDeLaPorcionCaseraUnidad = medidaDeLaPorcionCaseraUnidad;
    }

    public String getValorEnergeticoKCal() {
        return valorEnergeticoKCal;
    }

    public void setValorEnergeticoKCal(String valorEnergeticoKCal) {
        this.valorEnergeticoKCal = valorEnergeticoKCal;
    }

    public String getValorEnergeticoKJ() {
        return valorEnergeticoKJ;
    }

    public void setValorEnergeticoKJ(String valorEnergeticoKJ) {
        this.valorEnergeticoKJ = valorEnergeticoKJ;
    }

    public String getValorEnergeticoKVd() {
        return valorEnergeticoKVd;
    }

    public void setValorEnergeticoKVd(String valorEnergeticoKVd) {
        this.valorEnergeticoKVd = valorEnergeticoKVd;
    }

    public String getProteinasGr() {
        return proteinasGr;
    }

    public void setProteinasGr(String proteinasGr) {
        this.proteinasGr = proteinasGr;
    }

    public String getProteinasVd() {
        return proteinasVd;
    }

    public void setProteinasVd(String proteinasVd) {
        this.proteinasVd = proteinasVd;
    }

    public String getCarbohidratosGr() {
        return carbohidratosGr;
    }

    public void setCarbohidratosGr(String carbohidratosGr) {
        this.carbohidratosGr = carbohidratosGr;
    }

    public String getCarbohidratosVd() {
        return carbohidratosVd;
    }

    public void setCarbohidratosVd(String carbohidratosVd) {
        this.carbohidratosVd = carbohidratosVd;
    }

    public String getAzucaresGr() {
        return azucaresGr;
    }

    public void setAzucaresGr(String azucaresGr) {
        this.azucaresGr = azucaresGr;
    }

    public String getPolialcoholesGr() {
        return polialcoholesGr;
    }

    public void setPolialcoholesGr(String polialcoholesGr) {
        this.polialcoholesGr = polialcoholesGr;
    }

    public String getAlmidonGr() {
        return almidonGr;
    }

    public void setAlmidonGr(String almidonGr) {
        this.almidonGr = almidonGr;
    }

    public String getOtrosCarbohidratosGr() {
        return otrosCarbohidratosGr;
    }

    public void setOtrosCarbohidratosGr(String otrosCarbohidratosGr) {
        this.otrosCarbohidratosGr = otrosCarbohidratosGr;
    }

    public String getGrasasTotalesGr() {
        return grasasTotalesGr;
    }

    public void setGrasasTotalesGr(String grasasTotalesGr) {
        this.grasasTotalesGr = grasasTotalesGr;
    }

    public String getGrasasTotalesVd() {
        return grasasTotalesVd;
    }

    public void setGrasasTotalesVd(String grasasTotalesVd) {
        this.grasasTotalesVd = grasasTotalesVd;
    }

    public String getGrasasSaturadasGr() {
        return grasasSaturadasGr;
    }

    public void setGrasasSaturadasGr(String grasasSaturadasGr) {
        this.grasasSaturadasGr = grasasSaturadasGr;
    }

    public String getGrasasSaturadasVd() {
        return grasasSaturadasVd;
    }

    public void setGrasasSaturadasVd(String grasasSaturadasVd) {
        this.grasasSaturadasVd = grasasSaturadasVd;
    }

    public String getGrasasTransGr() {
        return grasasTransGr;
    }

    public void setGrasasTransGr(String grasasTransGr) {
        this.grasasTransGr = grasasTransGr;
    }

    public String getGrasasMonoinsaturadasGr() {
        return grasasMonoinsaturadasGr;
    }

    public void setGrasasMonoinsaturadasGr(String grasasMonoinsaturadasGr) {
        this.grasasMonoinsaturadasGr = grasasMonoinsaturadasGr;
    }

    public String getGrasasPoliinsaturadasGr() {
        return grasasPoliinsaturadasGr;
    }

    public void setGrasasPoliinsaturadasGr(String grasasPoliinsaturadasGr) {
        this.grasasPoliinsaturadasGr = grasasPoliinsaturadasGr;
    }

    public String getColesterolMg() {
        return colesterolMg;
    }

    public void setColesterolMg(String colesterolMg) {
        this.colesterolMg = colesterolMg;
    }

    public String getFibraAlimentariaGr() {
        return fibraAlimentariaGr;
    }

    public void setFibraAlimentariaGr(String fibraAlimentariaGr) {
        this.fibraAlimentariaGr = fibraAlimentariaGr;
    }

    public String getFibraAlimentariaVd() {
        return fibraAlimentariaVd;
    }

    public void setFibraAlimentariaVd(String fibraAlimentariaVd) {
        this.fibraAlimentariaVd = fibraAlimentariaVd;
    }

    public String getSodioMg() {
        return sodioMg;
    }

    public void setSodioMg(String sodioMg) {
        this.sodioMg = sodioMg;
    }

    public String getSodioVd() {
        return sodioVd;
    }

    public void setSodioVd(String sodioVd) {
        this.sodioVd = sodioVd;
    }

    public String getVitaminaAUg() {
        return vitaminaAUg;
    }

    public void setVitaminaAUg(String vitaminaAUg) {
        this.vitaminaAUg = vitaminaAUg;
    }

    public String getVitaminaAVd() {
        return vitaminaAVd;
    }

    public void setVitaminaAVd(String vitaminaAVd) {
        this.vitaminaAVd = vitaminaAVd;
    }

    public String getVitaminaDUg() {
        return vitaminaDUg;
    }

    public void setVitaminaDUg(String vitaminaDUg) {
        this.vitaminaDUg = vitaminaDUg;
    }

    public String getVitaminaDVd() {
        return vitaminaDVd;
    }

    public void setVitaminaDVd(String vitaminaDVd) {
        this.vitaminaDVd = vitaminaDVd;
    }

    public String getVitaminaCUg() {
        return vitaminaCUg;
    }

    public void setVitaminaCUg(String vitaminaCUg) {
        this.vitaminaCUg = vitaminaCUg;
    }

    public String getVitaminaCVd() {
        return vitaminaCVd;
    }

    public void setVitaminaCVd(String vitaminaCVd) {
        this.vitaminaCVd = vitaminaCVd;
    }

    public String getVitaminaEUg() {
        return vitaminaEUg;
    }

    public void setVitaminaEUg(String vitaminaEUg) {
        this.vitaminaEUg = vitaminaEUg;
    }

    public String getVitaminaEVd() {
        return vitaminaEVd;
    }

    public void setVitaminaEVd(String vitaminaEVd) {
        this.vitaminaEVd = vitaminaEVd;
    }

    public String getTiaminaMg() {
        return tiaminaMg;
    }

    public void setTiaminaMg(String tiaminaMg) {
        this.tiaminaMg = tiaminaMg;
    }

    public String getTiaminaVd() {
        return tiaminaVd;
    }

    public void setTiaminaVd(String tiaminaVd) {
        this.tiaminaVd = tiaminaVd;
    }

    public String getRiboflavinaMg() {
        return riboflavinaMg;
    }

    public void setRiboflavinaMg(String riboflavinaMg) {
        this.riboflavinaMg = riboflavinaMg;
    }

    public String getRiboflavinaVd() {
        return riboflavinaVd;
    }

    public void setRiboflavinaVd(String riboflavinaVd) {
        this.riboflavinaVd = riboflavinaVd;
    }

    public String getNiacinaMg() {
        return niacinaMg;
    }

    public void setNiacinaMg(String niacinaMg) {
        this.niacinaMg = niacinaMg;
    }

    public String getNiacinaVd() {
        return niacinaVd;
    }

    public void setNiacinaVd(String niacinaVd) {
        this.niacinaVd = niacinaVd;
    }

    public String getVitaminaB6Ug() {
        return vitaminaB6Ug;
    }

    public void setVitaminaB6Ug(String vitaminaB6Ug) {
        this.vitaminaB6Ug = vitaminaB6Ug;
    }

    public String getVitaminaB6Vd() {
        return vitaminaB6Vd;
    }

    public void setVitaminaB6Vd(String vitaminaB6Vd) {
        this.vitaminaB6Vd = vitaminaB6Vd;
    }

    public String getAcidoFolicoUg() {
        return acidoFolicoUg;
    }

    public void setAcidoFolicoUg(String acidoFolicoUg) {
        this.acidoFolicoUg = acidoFolicoUg;
    }

    public String getAcidoFolicoVd() {
        return acidoFolicoVd;
    }

    public void setAcidoFolicoVd(String acidoFolicoVd) {
        this.acidoFolicoVd = acidoFolicoVd;
    }

    public String getVitaminaB12Ug() {
        return vitaminaB12Ug;
    }

    public void setVitaminaB12Ug(String vitaminaB12Ug) {
        this.vitaminaB12Ug = vitaminaB12Ug;
    }

    public String getVitaminaB12Vd() {
        return vitaminaB12Vd;
    }

    public void setVitaminaB12Vd(String vitaminaB12Vd) {
        this.vitaminaB12Vd = vitaminaB12Vd;
    }

    public String getBiotinaUg() {
        return biotinaUg;
    }

    public void setBiotinaUg(String biotinaUg) {
        this.biotinaUg = biotinaUg;
    }

    public String getBiotinaVd() {
        return biotinaVd;
    }

    public void setBiotinaVd(String biotinaVd) {
        this.biotinaVd = biotinaVd;
    }

    public String getAcidoPantotenicoUg() {
        return acidoPantotenicoUg;
    }

    public void setAcidoPantotenicoUg(String acidoPantotenicoUg) {
        this.acidoPantotenicoUg = acidoPantotenicoUg;
    }

    public String getAcidoPantotenicoVd() {
        return acidoPantotenicoVd;
    }

    public void setAcidoPantotenicoVd(String acidoPantotenicoVd) {
        this.acidoPantotenicoVd = acidoPantotenicoVd;
    }

    public String getCalcioMg() {
        return calcioMg;
    }

    public void setCalcioMg(String calcioMg) {
        this.calcioMg = calcioMg;
    }

    public String getCalcioVd() {
        return calcioVd;
    }

    public void setCalcioVd(String calcioVd) {
        this.calcioVd = calcioVd;
    }

    public String getHierroMg() {
        return hierroMg;
    }

    public void setHierroMg(String hierroMg) {
        this.hierroMg = hierroMg;
    }

    public String getHierroVd() {
        return hierroVd;
    }

    public void setHierroVd(String hierroVd) {
        this.hierroVd = hierroVd;
    }

    public String getMagnesioMg() {
        return magnesioMg;
    }

    public void setMagnesioMg(String magnesioMg) {
        this.magnesioMg = magnesioMg;
    }

    public String getMagnesioVd() {
        return magnesioVd;
    }

    public void setMagnesioVd(String magnesioVd) {
        this.magnesioVd = magnesioVd;
    }

    public String getZincMg() {
        return zincMg;
    }

    public void setZincMg(String zincMg) {
        this.zincMg = zincMg;
    }

    public String getZincVd() {
        return zincVd;
    }

    public void setZincVd(String zincVd) {
        this.zincVd = zincVd;
    }

    public String getYodoMg() {
        return yodoMg;
    }

    public void setYodoMg(String yodoMg) {
        this.yodoMg = yodoMg;
    }

    public String getYodoVd() {
        return yodoVd;
    }

    public void setYodoVd(String yodoVd) {
        this.yodoVd = yodoVd;
    }

    public String getVitaminaKUg() {
        return vitaminaKUg;
    }

    public void setVitaminaKUg(String vitaminaKUg) {
        this.vitaminaKUg = vitaminaKUg;
    }

    public String getVitaminaKVd() {
        return vitaminaKVd;
    }

    public void setVitaminaKVd(String vitaminaKVd) {
        this.vitaminaKVd = vitaminaKVd;
    }

    public String getFosforoMg() {
        return fosforoMg;
    }

    public void setFosforoMg(String fosforoMg) {
        this.fosforoMg = fosforoMg;
    }

    public String getFosforoVd() {
        return fosforoVd;
    }

    public void setFosforoVd(String fosforoVd) {
        this.fosforoVd = fosforoVd;
    }

    public String getFluorMg() {
        return fluorMg;
    }

    public void setFluorMg(String fluorMg) {
        this.fluorMg = fluorMg;
    }

    public String getFluorVd() {
        return fluorVd;
    }

    public void setFluorVd(String fluorVd) {
        this.fluorVd = fluorVd;
    }

    public String getCobreUg() {
        return cobreUg;
    }

    public void setCobreUg(String cobreUg) {
        this.cobreUg = cobreUg;
    }

    public String getCobreVd() {
        return cobreVd;
    }

    public void setCobreVd(String cobreVd) {
        this.cobreVd = cobreVd;
    }

    public String getSelenioUg() {
        return selenioUg;
    }

    public void setSelenioUg(String selenioUg) {
        this.selenioUg = selenioUg;
    }

    public String getSelenioVd() {
        return selenioVd;
    }

    public void setSelenioVd(String selenioVd) {
        this.selenioVd = selenioVd;
    }

    public String getMolibdenoUg() {
        return molibdenoUg;
    }

    public void setMolibdenoUg(String molibdenoUg) {
        this.molibdenoUg = molibdenoUg;
    }

    public String getMolibdenoVd() {
        return molibdenoVd;
    }

    public void setMolibdenoVd(String molibdenoVd) {
        this.molibdenoVd = molibdenoVd;
    }

    public String getCromoUg() {
        return cromoUg;
    }

    public void setCromoUg(String cromoUg) {
        this.cromoUg = cromoUg;
    }

    public String getCromoVd() {
        return cromoVd;
    }

    public void setCromoVd(String cromoVd) {
        this.cromoVd = cromoVd;
    }

    public String getManganesoUg() {
        return manganesoUg;
    }

    public void setManganesoUg(String manganesoUg) {
        this.manganesoUg = manganesoUg;
    }

    public String getManganesoVd() {
        return manganesoVd;
    }

    public void setManganesoVd(String manganesoVd) {
        this.manganesoVd = manganesoVd;
    }

    public String getColinaUg() {
        return colinaUg;
    }

    public void setColinaUg(String colinaUg) {
        this.colinaUg = colinaUg;
    }

    public String getColinaVd() {
        return colinaVd;
    }

    public void setColinaVd(String colinaVd) {
        this.colinaVd = colinaVd;
    }

    public String getPotasioMg() {
        return potasioMg;
    }

    public void setPotasioMg(String potasioMg) {
        this.potasioMg = potasioMg;
    }

    public String getClorurosMg() {
        return clorurosMg;
    }

    public void setClorurosMg(String clorurosMg) {
        this.clorurosMg = clorurosMg;
    }

    public String getInformacionNutricionalComplementaria() {
        return informacionNutricionalComplementaria;
    }

    public void setInformacionNutricionalComplementaria(String informacionNutricionalComplementaria) {
        this.informacionNutricionalComplementaria = informacionNutricionalComplementaria;
    }

    public String getInformacionNutricionalAdicional() {
        return informacionNutricionalAdicional;
    }

    public void setInformacionNutricionalAdicional(String informacionNutricionalAdicional) {
        this.informacionNutricionalAdicional = informacionNutricionalAdicional;
    }

    public String getInstruccionesDeUso() {
        return instruccionesDeUso;
    }

    public void setInstruccionesDeUso(String instruccionesDeUso) {
        this.instruccionesDeUso = instruccionesDeUso;
    }

    public String getDeclaracionSimplificadaNoAportaCantidades() {
        return declaracionSimplificadaNoAportaCantidades;
    }

    public void setDeclaracionSimplificadaNoAportaCantidades(String declaracionSimplificadaNoAportaCantidades) {
        this.declaracionSimplificadaNoAportaCantidades = declaracionSimplificadaNoAportaCantidades;
    }

    public String getDeclaracionSimplificadaNotas() {
        return declaracionSimplificadaNotas;
    }

    public void setDeclaracionSimplificadaNotas(String declaracionSimplificadaNotas) {
        this.declaracionSimplificadaNotas = declaracionSimplificadaNotas;
    }

    public String getElaboradorNombre() {
        return elaboradorNombre;
    }

    public void setElaboradorNombre(String elaboradorNombre) {
        this.elaboradorNombre = elaboradorNombre;
    }

    public String getElaboradorDireccion() {
        return elaboradorDireccion;
    }

    public void setElaboradorDireccion(String elaboradorDireccion) {
        this.elaboradorDireccion = elaboradorDireccion;
    }

    public String getDistribuidorNombre() {
        return distribuidorNombre;
    }

    public void setDistribuidorNombre(String distribuidorNombre) {
        this.distribuidorNombre = distribuidorNombre;
    }

    public String getDistribuidorDireccion() {
        return distribuidorDireccion;
    }

    public void setDistribuidorDireccion(String distribuidorDireccion) {
        this.distribuidorDireccion = distribuidorDireccion;
    }

    public String getImportadorNombre() {
        return importadorNombre;
    }

    public void setImportadorNombre(String importadorNombre) {
        this.importadorNombre = importadorNombre;
    }

    public String getImportadorDireccion() {
        return importadorDireccion;
    }

    public void setImportadorDireccion(String importadorDireccion) {
        this.importadorDireccion = importadorDireccion;
    }

    public String getCondicionesDeAlmacenamientoEnvaseCerrado() {
        return condicionesDeAlmacenamientoEnvaseCerrado;
    }

    public void setCondicionesDeAlmacenamientoEnvaseCerrado(String condicionesDeAlmacenamientoEnvaseCerrado) {
        this.condicionesDeAlmacenamientoEnvaseCerrado = condicionesDeAlmacenamientoEnvaseCerrado;
    }

    public String getCondicionesDeAlmacenamientoEnvaseAbierto() {
        return condicionesDeAlmacenamientoEnvaseAbierto;
    }

    public void setCondicionesDeAlmacenamientoEnvaseAbierto(String condicionesDeAlmacenamientoEnvaseAbierto) {
        this.condicionesDeAlmacenamientoEnvaseAbierto = condicionesDeAlmacenamientoEnvaseAbierto;
    }

    public Boolean getContieneOmg() {
        return contieneOmg;
    }

    public void setContieneOmg(Boolean contieneOmg) {
        this.contieneOmg = contieneOmg;
    }

    public String getServicioDeAtencionAlConsumidor() {
        return servicioDeAtencionAlConsumidor;
    }

    public void setServicioDeAtencionAlConsumidor(String servicioDeAtencionAlConsumidor) {
        this.servicioDeAtencionAlConsumidor = servicioDeAtencionAlConsumidor;
    }

    public Boolean getAplicaDecretoBebidasAlcoholicas() {
        return aplicaDecretoBebidasAlcoholicas;
    }

    public void setAplicaDecretoBebidasAlcoholicas(Boolean aplicaDecretoBebidasAlcoholicas) {
        this.aplicaDecretoBebidasAlcoholicas = aplicaDecretoBebidasAlcoholicas;
    }

    public String getRotuloNutricionalImg() {
        return rotuloNutricionalImg;
    }

    public void setRotuloNutricionalImg(String rotuloNutricionalImg) {
        this.rotuloNutricionalImg = rotuloNutricionalImg;
    }

    public Boolean getFueActualizadoElRotuloNutricional() {
        return fueActualizadoElRotuloNutricional;
    }

    public void setFueActualizadoElRotuloNutricional(Boolean fueActualizadoElRotuloNutricional) {
        this.fueActualizadoElRotuloNutricional = fueActualizadoElRotuloNutricional;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AtributosExtendidosAlimentos other = (AtributosExtendidosAlimentos) obj;
        if (other.getId().equals(this.getId()))
            return true;
        return false;
    }
}
