/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.form.bean;

import aplicacion.modelo.dominio.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author HP
 */
@ManagedBean
@ViewScoped
public class MultiSelectView implements Serializable{
    private List<SelectItem> categories;    
    private Cliente unCliente;
    private String selection;

    public MultiSelectView(List<SelectItem> categories, String selection) {
        this.categories = categories;
        this.selection = selection;
    }
    
    
    public MultiSelectView() {
        
        
         categories = new ArrayList<>();
        SelectItemGroup group1 = new SelectItemGroup("Argentina");
        SelectItemGroup group2 = new SelectItemGroup("Brasil");
        SelectItemGroup group3 = new SelectItemGroup("Uruguay");

        SelectItem option11 = new SelectItem("Jujuy", "Jujuy");
        SelectItem option12 = new SelectItem("Buenos Aires", "Buenos Aires");
        SelectItem option13 = new SelectItem("Catamarca", "Catamarca");
        SelectItem option14 = new SelectItem("Chaco", "Chaco");
        SelectItem option15 = new SelectItem("Chubut", "Chubut");
        SelectItem option16 = new SelectItem("Córdoba", "Córdoba");
        SelectItem option17 = new SelectItem("Corrientes", "Corrientes");
        SelectItem option111 = new SelectItem("C.A.B.A.", "C.A.B.A.");
        SelectItem option112 = new SelectItem("Tucumán", "Tucumán");
        SelectItem option113 = new SelectItem("Tierra del Fuego", "Tierra del Fuego");
        SelectItem option114 = new SelectItem("Santiago del Estero", "Santiago del Estero");
        SelectItem option115 = new SelectItem("Santa Fe", "Santa Fe");
        SelectItem option116 = new SelectItem("Santa Cruz", "Santa Cruz");
        SelectItem option117 = new SelectItem("San Juan", "San Juan");
        SelectItem option118 = new SelectItem("Salta", "Salta");
        SelectItem option119 = new SelectItem("Río Negro", "Río Negro");
        SelectItem option120 = new SelectItem("Neuquén", "Neuquén");
        SelectItem option121 = new SelectItem("Misiones", "Misiones");
        SelectItem option122 = new SelectItem("Mendoza", "Mendoza");
        SelectItem option123 = new SelectItem("La Rioja", "La Rioja");
        SelectItem option124 = new SelectItem("La Pampa", "La Pampa");
        SelectItem option125 = new SelectItem("Formosa", "Formosa");
        SelectItem option126 = new SelectItem("Entre Ríos", "Entre Ríos");
        group1.setSelectItems(new SelectItem[]{option11,option12,option13,option14,option15,option16,option17,option111,option112,option113,option114,option115,option116,option117,option118,option119,option120,option121,option122,option123,option124,option125,option126});
         
        categories.add(group1);
        
        SelectItem option227 = new SelectItem("Brasilia", "Brasilia");
        SelectItem option226 = new SelectItem("Tocantins", "Tocantins");
        SelectItem option225 = new SelectItem("Sergipe", "Sergipe");
        SelectItem option224 = new SelectItem("São Paulo", "São Paulo");
        SelectItem option223 = new SelectItem("Santa Catarina", "Santa Catarina");
        SelectItem option222 = new SelectItem("Roraima", "Roraima");
        SelectItem option221 = new SelectItem("Rondônia", "Rondônia");
        SelectItem option220 = new SelectItem("Río Grande do Sul", "Río Grande do Sul");
        SelectItem option219 = new SelectItem("Río Grande do Norte", "Río Grande do Norte");
        SelectItem option218 = new SelectItem("Río de Janeiro", "Río de Janeiro");
        SelectItem option217 = new SelectItem("Piauí", "Piauí");
        SelectItem option216 = new SelectItem("Pernambuco", "Pernambuco");
        SelectItem option215 = new SelectItem("Paraná", "Paraná");
        SelectItem option214 = new SelectItem("Paraíba", "Paraíba");
        SelectItem option213 = new SelectItem("Pará", "Pará");
        SelectItem option212 = new SelectItem("Minas Gerais", "Minas Gerais");
        SelectItem option211 = new SelectItem("Mato Grosso do Sul ", "Mato Grosso do Sul");
        SelectItem option210 = new SelectItem("Mato Grosso", "Mato Grosso");
        SelectItem option29 = new SelectItem("Maranhão", "Maranhão");
        SelectItem option28 = new SelectItem("Goiás", "Goiás");
        SelectItem option27 = new SelectItem("Espíritu Santo", "Espíritu Santo");
        SelectItem option26 = new SelectItem("Ceará", "Ceará");
        SelectItem option25 = new SelectItem("Bahía", "Bahía");
        SelectItem option24 = new SelectItem("La Amazonas", "La Amazonas");
        SelectItem option23 = new SelectItem("Amapá", "Amapá");
        SelectItem option22 = new SelectItem("Alagoas", "Alagoas");
        SelectItem option21 = new SelectItem("Acre", "Acre");
        group2.setSelectItems(new SelectItem[]{option21,option22,option23,option24,option25,option26,option27,option28,option29,option210,option211,option212,option213,option214,option215,option216,option217,option218,option219,option220,option221,option222,option223,option224,option225,option226,option227});
        categories.add(group2);     
       
        SelectItem option319 = new SelectItem("Treinta y Tres", "Treinta y Tres");
        SelectItem option318 = new SelectItem("Tacuarembó", "Tacuarembó");
        SelectItem option317 = new SelectItem("Soriano", "Soriano");
        SelectItem option316 = new SelectItem("San José", "San José");
        SelectItem option315 = new SelectItem("Salto", "Salto");
        SelectItem option314 = new SelectItem("Rocha", "Rocha");
        SelectItem option313 = new SelectItem("Rivera", "Rivera");
        SelectItem option312 = new SelectItem("Río Negro", "Río Negro");
        SelectItem option311 = new SelectItem("Paysandú", "Paysandú");
        SelectItem option310 = new SelectItem("Montevideo", "Montevideo");
        SelectItem option39 = new SelectItem("Maldonado", "Maldonado");
        SelectItem option38 = new SelectItem("Lavalleja", "Lavalleja");
        SelectItem option37 = new SelectItem("Florida", "Florida");
        SelectItem option36 = new SelectItem("Flores", "Flores");
        SelectItem option35 = new SelectItem("Durazno", "Durazno");
        SelectItem option34 = new SelectItem("Colonia", "Colonia");
        SelectItem option33 = new SelectItem("Cerro Largo", "Cerro Largo");
        SelectItem option32 = new SelectItem("Canelones", "Canelones");
        SelectItem option31 = new SelectItem("Artigas", "Artigas");
        group3.setSelectItems(new SelectItem[]{option31,option32,option33,option34,option35,option36,option37,option38,option39,option310,option311,option312,option313,option314,option315,option316,option317,option318,option319});
        categories.add(group3);
    }
    public List<SelectItem> getCategories() {
        return categories;
    }    
 
    public String getSelection() {
        return selection;
    }
    public void setSelection(String selection) {
        this.selection = selection;
    }

    public Cliente getUnCliente() {
        return unCliente;
    }

    public void setUnCliente(Cliente unCliente) {
        this.unCliente = unCliente;
    }
    
}
