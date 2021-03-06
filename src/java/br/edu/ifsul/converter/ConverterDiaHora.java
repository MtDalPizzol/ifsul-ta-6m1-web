package br.edu.ifsul.converter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@FacesConverter(value = "converterDiaHora")
public class ConverterDiaHora implements Converter, Serializable{
    
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null){
            return null;
        }
        try {
            Calendar retorno = Calendar.getInstance();
            retorno.setTime(sdf.parse(string));
            return retorno;
        } catch (Exception e){
            return null;
        }            
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Calendar obj = (Calendar) o;
        return sdf.format(obj.getTime());
    }

}
