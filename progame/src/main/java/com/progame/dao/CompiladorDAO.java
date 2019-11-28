package com.progame.dao;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.script.Compilable;
 
public class CompiladorDAO{
 
    private static final Logger log = Logger.getLogger(CompiladorDAO.class.getName());    
 
    public ArrayList<String> executeCommand(final String command) throws IOException {
         
        final ArrayList<String> commands = new ArrayList<String>();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(command);
        BufferedReader br = null;        
        ArrayList <String> resultado = new ArrayList<String>();            
        String tmp = null;
        try {                        
            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
             String line=null;
            while((line = br.readLine()) != null) {
            	System.out.println("Saida: "+line);
                resultado.add(line);
            }
            
        } catch (IOException ioe) {
            log.severe("Erro ao executar comando shell" + ioe.getMessage());
            throw ioe;
        } finally {
            secureClose(br);
        }
        return resultado;
    }
     
    private void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            log.severe("Erro = " + ex.getMessage());
        }
    }

}

