const form = document.forms.modificar
console.log(form);

const fnSubmit = (e)=>{
    e.preventDefault() // que no se actualice sitio
    console.log()

    
    const data ={
        id: form.id.value,
        nombre: form.nombre.value,
        apellido: form.apellido.value,
        correo: form.correo.value,
        cantidad : form.cantidad.value,
        categoria: form.categoria.value
    }
    const config={
        method:'PUT', 
        body: JSON.stringify(data)
    }
    fetch('/api/reservas', config)
    .then(res => res.json())//retorno de promesa
    .then(archivo => archivo.error
        ?alert('problema actualisacion de datos')
        : location.href='/exito.html'
        )
}



form.addEventListener('submit',fnSubmit)