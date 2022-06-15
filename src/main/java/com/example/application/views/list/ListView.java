package com.example.application.views.list;

import java.util.Collections;

import javax.annotation.security.PermitAll;

import com.example.application.data.entity.Contact;
import com.example.application.data.service.CrmService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PermitAll
@Route(value="", layout = MainLayout.class) 
@PageTitle("Colaboradores | MartinsTech")
/*1.A classe ListView herda de VerticalLayout, (todos os
 * componentes são verticalmente posicionados).*/
public class ListView extends VerticalLayout {
	//2.O componente Grid (grade) é preenchido a objetos Contact.
	Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();
    ContactForm form;
    CrmService service;

    public ListView(CrmService service) { 
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        
        /*3.A configuração da grade é codificada em um método
         * separado para manter o construtor mais fácil de ler.*/
        configureGrid();
        configureForm();
        
        
        //Adicione uma barra de ferramentas e a grade ao layout VerticalLayout da view
        add(getToolbar(), getContent());
        updateList(); 

        closeEditor();
    }
    
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid); 
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {        
        form = new ContactForm(service.findAllDepartamentos(), service.findAllStatuses());
        form.setWidth("25em");
        
        form.addListener(ContactForm.SaveEvent.class, this::saveContact); 
        form.addListener(ContactForm.DeleteEvent.class, this::deleteContact); 
        form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());
    }


    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("nome", "sobrenome", "email"); //Defina os atributos de Contact devem ser exibidos na grade.
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status"); //Adicione novas colunas personalizadas à grade.
        grid.addColumn(contact -> contact.getDepartamento().getName()).setHeader("Cargo");
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); //Configure as colunas para ajustar automaticamente seu tamanho para caber em seu conteúdo.

        grid.asSingleSelect().addValueChangeListener(event ->
            editContact(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filtrar por nome..");
        filterText.setClearButtonVisible(true);
        /*Configure o campo de pesquisa (fieldText) para disparar eventos de alteração 
         * de valor somente quando o usuário parar de digitar. Dessa forma, 
         * você evita chamadas desnecessárias ao banco de dados.*/
        filterText.setValueChangeMode(ValueChangeMode.LAZY); 

        filterText.addValueChangeListener(e -> updateList()); 
        
        Button addContactButton = new Button("Adicionar colaborador");
        addContactButton.addClickListener(click -> addContact());
        
        //A barra de ferramentas usa um HorizontalLayoutpara colocar o TextFielde Button próximo um do outro
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton); 
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    
    public void editContact(Contact contact) { 
        if (contact == null) {
            closeEditor();
        } else {
            form.setContact(contact);
            form.setVisible(true);
            addClassName("editing");
        }
    }
    
    private void saveContact(ContactForm.SaveEvent event) {
        service.saveContact(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteContact(ContactForm.DeleteEvent event) {
        service.deleteContact(event.getContact());
        updateList();
        closeEditor();
    }
    
    private void closeEditor() {
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addContact() { 
        grid.asSingleSelect().clear();
        editContact(new Contact());
    }
    private void updateList() { 
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }
}