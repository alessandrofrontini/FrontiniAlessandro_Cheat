package it.unicam.cheatBackend.services;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Service;

@Service
public class SanitizeService {
    public String sanificaInput(String testo){
        if(testo == null)
            return null;
        return StringEscapeUtils.escapeHtml4(testo);
    }
}
