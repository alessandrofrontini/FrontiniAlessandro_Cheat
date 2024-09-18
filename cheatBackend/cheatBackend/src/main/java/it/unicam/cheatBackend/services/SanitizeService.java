package it.unicam.cheatBackend.services;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Service;

@Service
public class SanitizeService {
    public String sanificaInput(String testo){
        //se la stringa di testo non è vuota, viene eseguito il Sanitize eliminando i tag HTML
        if(testo == null)
            return null;
        return StringEscapeUtils.escapeHtml4(testo);
    }
}
