

const deleteTicket= async(id)=>{
    
    const  config = {method: 'DELETE'};
    await fetch(`/api/tickets/${id}`, config); 
    //await espera a que se cargue el fetch y luego refresh pagina
    location.reload();
}

const deleteOrador= async(id)=>{
    
    const  config = {method: 'DELETE'};
    await fetch(`/api/oradores/${id}`, config); 
    //await espera a que se cargue el fetch y luego refresh pagina
    location.reload();
}