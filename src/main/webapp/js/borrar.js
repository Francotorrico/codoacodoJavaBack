const form= document.forms.borrar;



const fnSubmit=(e)=>{
    e.preventDefault()
    const config={
        method: 'DELETE', 
        body: JSON.stringify(parseInt(form.id.value))
    }
    fetch('/api/reservas', config)
    .then(res => res.json())//retorno de promesa
    .then(archivo => archivo.error
        ?alert('problema actualisacion de datos')
        : location.href='/exito.html'
        )
}


form.addEventListener('submit', fnSubmit);