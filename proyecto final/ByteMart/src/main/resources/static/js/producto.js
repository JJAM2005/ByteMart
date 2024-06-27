const apiUrl = 'http://localhost:8090/api/producto';

// Añadir producto
document.getElementById('botonAñadir').addEventListener('click', () => {
    const producto = {
        nombre: document.getElementById('productoAñadir').value,
        valor: document.getElementById('valorAñadir').value,
        existencia: document.getElementById('existenciaAñadir').value,
        imagen: document.getElementById('ImagenAñadir').value
    };
    fetch(apiUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(producto)
    })
   .then(response => response.json())
   .then(data => console.log(data))
   .catch(error => console.error(error));
});

// Editar producto
document.getElementById('botonEditar').addEventListener('click', () => {
    const id = document.getElementById('productoEditar').value;
    const atributo = document.getElementById('atributoEditar').value;
    const nuevoAtributo = document.getElementById('nuevoAtributo').value;
    fetch(`${apiUrl}/${id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ [atributo]: nuevoAtributo })
    })
   .then(response => response.json())
   .then(data => console.log(data))
   .catch(error => console.error(error));
});

// Eliminar producto
document.getElementById('botonEliminar').addEventListener('click', () => {
    const id = document.getElementById('productoEliminar').value;
    fetch(`${apiUrl}/${id}`, {
        method: 'DELETE'
    })
   .then(response => response.json())
   .then(data =>console.log(data))
  .catch(error => console.error(error));
});

// Mostrar productos
fetch(apiUrl)
   .then(response => response.json())
   .then(data => {
        const productList = document.getElementById('product-list');
        data.forEach(producto => {
            const productoHTML = `
                <div>
                    <h2>${producto.nombre}</h2>
                    <p>Valor: ${producto.valor}</p>
                    <p>Existencia: ${producto.existencia}</p>
                    <p>Imagen: ${producto.imagen}</p>
                </div>
            `;
            productList.innerHTML += productoHTML;
        });
    })
   .catch(error => console.error(error));