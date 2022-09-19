function prueba1(){
			
	var esVisible = $("#messages").is(":visible");
			alert(esVisible);
		}

function expandirFieldset(id){   
    if (PF(id).cfg.collapsed==true){
        //Expand all fieldsets
    	PF(id).toggle();
    } 
}


function cerrarFieldset(id){   
    if (PF(id).cfg.collapsed==false){
        //Expand all fieldsets
    	PF(id).toggle();
    } 
}

function handleLoginRequest(xhr, status, args) {
    if(args.validationFailed || !args.loggedIn) {
        PF('dlgAlta').jq.effect("shake", {times:5}, 100);
    }
    else {
        PF('dlgAlta').hide();
    }
}

function handleLoginRequest2(xhr, status, args) {
	//no cierra la ventana cuando el resultado es correcto
    if(args.validationFailed || !args.loggedIn) {
        PF('dlgAlta').jq.effect("shake", {times:5}, 100);
    }
   /* else {
    	PF('dlgAlta').hide();
    }*/
}

function limpiarCamposBuscadorJustificante(){
		
	var nroc = document.getElementById("buscadorJustificantesForm:nroDocuemento");
	nroc.value = "";
	
	var nroj = document.getElementById("buscadorJustificantesForm:nroJustificante");
	nroj.value = "";
	
	var fechaD = document.getElementById("buscadorJustificantesForm:fecha_pago_desde_input");
	fechaD.value = "";
	
	PF('tipoJustificante').selectValue("-1");
	
	var fechaH = document.getElementById("buscadorJustificantesForm:fecha_pago_hasta_input");
	fechaH.value = "";
	
	PF('sistemaLlamante').selectValue("-1");
	
	PF('medioDePago').selectValue("-1");
	
	PF('tipoIdentificacion').selectValue("0");
	
	var id = document.getElementById("buscadorJustificantesForm:identificacion");
	id.value = "";
	
	var nombre = document.getElementById("buscadorJustificantesForm:nombre");
	nombre.value = "";
	
}





(function ($) {
    "use strict";


    /*==================================================================
    [ Focus input ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })
  
  
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    
})(jQuery);