package com.example.rastreadordecasoscovid_19;

public class ModeloPais {
    private String Bandera;
    private String NommbrePais;
    private String CasosTotales;
    private String CasosHoy;
    private String MuertesTotales;
    private String MuertesHoy;
    private String Recuperados;
    private String CasosActivos;
    private String CasosCriticos;

    public ModeloPais() {
    }

    public ModeloPais(String bandera, String nommbrePais, String casosTotales, String casosHoy, String muertesTotales, String muertesHoy, String recuperados, String casosActivos, String casosCriticos) {
        Bandera = bandera;
        NommbrePais = nommbrePais;
        CasosTotales = casosTotales;
        CasosHoy = casosHoy;
        MuertesTotales = muertesTotales;
        MuertesHoy = muertesHoy;
        Recuperados = recuperados;
        CasosActivos = casosActivos;
        CasosCriticos = casosCriticos;
    }

    public String getBandera() {
        return Bandera;
    }

    public void setBandera(String bandera) {
        Bandera = bandera;
    }

    public String getNommbrePais() {
        return NommbrePais;
    }

    public void setNommbrePais(String nommbrePais) {
        NommbrePais = nommbrePais;
    }

    public String getCasosTotales() {
        return CasosTotales;
    }

    public void setCasosTotales(String casosTotales) {
        CasosTotales = casosTotales;
    }

    public String getCasosHoy() {
        return CasosHoy;
    }

    public void setCasosHoy(String casosHoy) {
        CasosHoy = casosHoy;
    }

    public String getMuertesTotales() {
        return MuertesTotales;
    }

    public void setMuertesTotales(String muertesTotales) {
        MuertesTotales = muertesTotales;
    }

    public String getMuertesHoy() {
        return MuertesHoy;
    }

    public void setMuertesHoy(String muertesHoy) {
        MuertesHoy = muertesHoy;
    }

    public String getRecuperados() {
        return Recuperados;
    }

    public void setRecuperados(String recuperados) {
        Recuperados = recuperados;
    }

    public String getCasosActivos() {
        return CasosActivos;
    }

    public void setCasosActivos(String casosActivos) {
        CasosActivos = casosActivos;
    }

    public String getCasosCriticos() {
        return CasosCriticos;
    }

    public void setCasosCriticos(String casosCriticos) {
        CasosCriticos = casosCriticos;
    }
}
