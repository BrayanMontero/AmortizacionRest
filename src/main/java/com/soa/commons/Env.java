package com.soa.commons;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class Env {

    private static final String PROPERTIES_FILE_PATH = "properties.file.path";

    /** Contenedor dinámico de archivo de propiedades. */
    private static PropertiesConfiguration configuration = null;

    /*
     * Inicializador del contenedor dinámico de archivo de propiedades.
     */
    static {
        String path = null;
        try {
            path = System.getProperty(PROPERTIES_FILE_PATH);
            configuration = new PropertiesConfiguration(path);
            configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
            configuration.setAutoSave(true);
        } catch (Throwable e) {
            throw new AppException(
                    "Nombre del archivo no identificado[" + path + "]", e);
        }
    }

    /**
     * Obtiene una propiedad configurada sin permitir valores vacíos.
     *
     * @param key Nombre clave de la propiedad a buscar.
     * @return Valor de la propiedad buscada.
     */
    public static synchronized String getProperty(final String key) {
        try {
            final String result = (String) configuration.getProperty(key);
            if (result == null || result.isEmpty()) {
                throw new AppException(
                        "Nombre de la propiedad no identificado[" + key + "]");
            }
            return result;
        } catch (Exception e) {
            throw new AppException(
                    "Error al leer la propiedad[" + key + "] "
                            + AppException.getStackTrace(e));
        }
    }
}

