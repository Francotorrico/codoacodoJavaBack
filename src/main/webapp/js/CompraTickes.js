//variables

const precioInicial =200;

const categories ={
    1:{ percent: 80, value:'0'},
    2:{ percent: 50, value:'1'},
    3:{ percent: 15, value:'2'}
}
const totaltext='Total a Pagar: $ '

let categoria=null
let tickets=null
let total=null

//dom

const form= document.forms.formulario

const inputs = form.getElementsByTagName('input')

const select = form.getElementsByTagName('select')[0]


const totaltag=document.getElementById('total')

//los botones verdes Borrar y resumen
const resetbtn=document.getElementById('reset')
const submitbtn=document.getElementById('submit')

//utils

const precioTotal = ()=>{

    if (!tickets||!categoria) {
        return;
    }
    //tickets =2
    const totalvalue=precioInicial*tickets
    //console.log(totalvalue)

    const discount=(totalvalue/100)*categories[categoria].percent

    total=totalvalue-discount

    //console.log(total)
    totaltag.innerText=totaltext+total
}

//eventos 

//reseteos
const resetCategories=()=>{
    total=null

    selected=null
    evenAssigmentAll()

    totaltag.innerText=totaltext
}
//cambie nombre de reset -> resetForm
const resetForm=(e)=>{

    e.preventDefault()

    for (let input of inputs) {
           console.log(input.value)
           input.value=''

        
    }

    select.value='none'
    resetCategories()
}

// nuevos metodos
//form
const verifyForm=(form)=>{
    
    const{firstname,lastname,email,tickets, categoria} = form
    const verified={
        firstname: firstname.value!=='',
        lastname: lastname.value!=='',
        email: email.value.includes('@'),
        tickets: tickets.value>0, 
        categoria: categoria.value!=='none'
    }
    const values = Object.values(verified)


    const accepted = values.every(value=>value)
    return accepted;
}
//funcion adaptadora
const adapterForm = (form)=>{
    const{firstname,lastname,email,tickets, categoria} = form
    const data = {
        nombre: firstname.value,
        apellido: lastname.value,
        correo: email.value,
        cantidad: tickets.value, 
        categoria: categoria.value
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
    fetch('/api/tickets',config) 
    // entra al get a buscar se lo mando a api/tickets con metodo post
}

const submit =(e)=>{
    e.preventDefault()

    //console.log(submitAccepted)
    const submitAccepted= verifyForm(form);
    submitAccepted
    ? submitForm(form)
    : alert('debes completar todos los campos')
}


// setters
const setCategory =(e)=>{

    const option = e.target.value
    console.log(option)

    if (option==='none') {
        resetCategories()
        return;
    }
    categoria=option
    const index= categories[categoria].value
    const container=cardcontainer[index]

    selected = index
    changecolor(container,index)
    evenAssigmentAll()

    precioTotal()
}




const setTicket =(e)=>{

    const {value} = e.target

    if(value<0||isNaN(value)){

        e.target.value=0
        total=null
        return;
    }
    tickets=value
    precioTotal()


    console.log(value)



}


form.categoria.addEventListener('change',setCategory)

form.tickets.addEventListener('keyup',setTicket)



form.addEventListener('submit',submit)
resetbtn.addEventListener('click',reset)
