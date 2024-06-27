document.addEventListener('DOMContentLoaded', function () {
    // esto es algo para que se inhabiliten algunas opciones en el menu desplegable del login pero no funciona
    const loginButton = document.getElementById('login-button');
    const loginMenu = document.getElementById('login-menu');
    let menuVisible = false;

    loginButton.addEventListener('click', function () {
        menuVisible = !menuVisible;
        loginMenu.style.display = menuVisible ? 'block' : 'none';
    });

    document.addEventListener('click', function (event) {
        if (!loginButton.contains(event.target) && !loginMenu.contains(event.target)) {
            menuVisible = false;
            loginMenu.style.display = 'none';
        }
    });


    let carrito = [];

    // Función para renderizar los productos en el menú del carrito
    function renderizarCarrito() {
        const carritoMenu = document.getElementById('carrito-menu');
        carritoMenu.innerHTML = ''; // Limpiar el contenido previo

        if (carrito.length === 0) {
            carritoMenu.innerHTML = '<li><a href="#">Carrito vacío</a></li>';
        } else {
            carrito.forEach(producto => {
                const li = document.createElement('li');
                li.innerHTML = `<a href="#">${producto.nombre} - $${producto.precio.toFixed(2)} COP</a>`;
                carritoMenu.appendChild(li);
            });
        }
    }

    // Función para añadir un producto al carrito
    function agregarAlCarrito(nombre, precio) {
        const producto = { nombre, precio };
        carrito.push(producto);
        renderizarCarrito();
    }

    // Escuchar el evento de clic en los botones "Agregar al carrito" no funciona
    const botonesAgregar = document.querySelectorAll('.add-to-cart');
    botonesAgregar.forEach(button => {
        button.addEventListener('click', () => {
            const productoElemento = button.parentElement;
            const nombre = productoElemento.querySelector('h5').textContent;
            const precioTexto = productoElemento.querySelector('.precio').textContent;
            const precio = parseFloat(precioTexto.replace(/[^0-9.-]+/g, ''));

            agregarAlCarrito(nombre, precio);
        });
    });

    // Inicializar el carrito
    renderizarCarrito();
});
