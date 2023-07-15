const form= document.forms.formOrador;
console.log(form);


// nuevos metodos
//form
const verifyForm=(form)=>{
    
    const{firstname,lastname,context} = form
    const verified={
        firstname: firstname.value!=='',
        lastname: lastname.value!=='',
        context: context.value!=='none'
    }
    const values = Object.values(verified)


    const accepted = values.every(value=>value)
    return accepted;
}
//funcion adaptadora
const adapterForm = (form)=>{
    const{firstname,lastname,context} = form
    const data = {
        nombre: firstname.value,
        apellido: lastname.value,
        tema: context.value
    }
    return data;
}

// fuera del usuario es asincrona
const submitForm=async(form)=>{

    const data = adapterForm(form);
   
    const config = {
        method:'POST',
        body: JSON.stringify(data)
    }
    console.log(JSON.stringify(data));
    fetch('/api/oradores',config)
    // entra al get a buscar se lo mando a api/oradores con metodo post
}

const submit =(e)=>{
    e.preventDefault()

    //console.log(submitAccepted)
    const submitAccepted= verifyForm(form);
    submitAccepted
    ? submitForm(form)
    : alert('debes completar todos los campos')
}

form.addEventListener('submit',submit)