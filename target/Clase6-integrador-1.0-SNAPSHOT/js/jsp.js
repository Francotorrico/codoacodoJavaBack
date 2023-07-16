

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
/*
const getReservas=async(e)=>{
    e.preventDefault(); try {
    const response = await fetch('/api/tickets', { method: 'GET' });
    if (response.ok) {
      // Aquí puedes hacer algo con la respuesta si es necesario
      // Por ejemplo, podrías obtener los datos de la respuesta y utilizarlos en tu página
      // Puedes utilizar response.json() para obtener los datos en formato JSON

      // Luego, puedes recargar la página
      location.reload();
    } else {
      console.error('Error al obtener los datos de la API');
    }
  } catch (error) {
    console.error('Error en la solicitud GET:', error);
  }
}*/