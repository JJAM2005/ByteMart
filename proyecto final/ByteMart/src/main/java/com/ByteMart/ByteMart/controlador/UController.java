package com.ByteMart.ByteMart.controlador;


//import java.io.IOException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;


import com.ByteMart.ByteMart.modelo.DetalleOrden;
import com.ByteMart.ByteMart.modelo.Orden;

import com.ByteMart.ByteMart.modelo.Producto;
import com.ByteMart.ByteMart.modelo.Usuario;
//import com.ByteMart.ByteMart.service.IDetalleOrdenService;
import com.ByteMart.ByteMart.service.IOrdenService;
import com.ByteMart.ByteMart.service.IUsuarioService;

import com.ByteMart.ByteMart.service.ProductoService;


import ch.qos.logback.classic.Logger;

import jakarta.servlet.http.HttpSession;

@Controller
public class UController {
	
	private final Logger logger= (Logger) LoggerFactory.getLogger(UController.class);
	
	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private IOrdenService ordensService;

	
	@Autowired
	private IOrdenService ordenService;
	
	/*@Autowired
	private IDetalleOrdenService detalleOrdenService;*/

	// para almacenar los detalles de la orden
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	// datos de la orden
	Orden orden = new Orden();
	
	//BCryptPasswordEncoder passEncode= new BCryptPasswordEncoder();
	@GetMapping("/inicio")
	public String inicio() {
		return "inicio";
	}
	@GetMapping("/create")
	public String create() {
		return "create";
	}
	@GetMapping("/productos")
	public String productos() {
		return "productos";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/producto")
	public String producto() {
		return "producto";
	}
	@GetMapping("/carrito")
	public String carrito() {
		return "carrito";
	}
	

	@GetMapping("/usuario")
	public String getUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuario";
	}
	@GetMapping("/inicioadmin")
	public String inicioadmin() {
		return "inicioadmin";
	}

	@GetMapping ("/sesiones")
	public String sesiones() {
		return "sesiones";
	}
	@GetMapping("/olvidocontraseña")
	public String  olvidocontraseña() {
		return "olvidocontraseña";
	}

	
	@PostMapping("/save")
	public String save(Usuario usuario) {
		logger.info("Usuario registro: {}", usuario);
		usuario.setRol("User");
		usuario.setPassword(usuario.getPassword());
		usuarioService.save(usuario);		
		return "redirect:/login";
	}
	
	
	@PostMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {
		logger.info("Accesos : {}", usuario);
		
		Optional<Usuario> user=usuarioService.findByEmail(usuario.getEmail());
		//logger.info("Usuario de db: {}", user.get());
		
		if (user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());
			
			if (user.get().getRol().equals("Admin")) {
				return "redirect:/inicioadmin";
			}else {
				return "redirect:/inicio";
			}
		}else {
			logger.info("Usuario no existe");
		}
		
		return "redirect:/login";
	}

	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		Usuario usuario= usuarioService.findById(  Integer.parseInt(session.getAttribute("idusuario").toString()) ).get();
		List<Orden> ordenes= ordenService.findByUsuario(usuario);
		logger.info("ordenes {}", ordenes);
		
		model.addAttribute("ordenes", ordenes);
		
		return "compras";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
		logger.info("Id de la orden: {}", id);
		Optional<Orden> orden=ordenService.findById(id);
		
		model.addAttribute("detalles", orden.get().getDetalle());
		
		
		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "usuario/detallecompra";
	}
	
	@GetMapping("/cerrar")
	public String cerrarSesion( HttpSession session ) {
		session.removeAttribute("idusuario");
		return "redirect:/";
	}
	//adminitrador
	
	
	
	@GetMapping("/pedidos")
	public String ordenes(Model model) {
		model.addAttribute("ordenes", ordensService.findAll());
		return "pedidos";
	}
	//no sirve
	/*@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		logger.info("Id de la orden {}",id);
		Orden orden= ordensService.findById(id).get();
		
		model.addAttribute("detalles", orden.getDetalle());
		
		return "detallepedido";
	}*/

	//home
	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
		logger.info("Sesion del usuario: {}", session.getAttribute("idusuario"));
		
		model.addAttribute("productos", productoService.findAll());
		
		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		return "home";
	}

	@GetMapping("verproducto/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		logger.info("Id producto enviado como parámetro {}", id);
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoService.get(id);
		producto = productoOptional.get();

		model.addAttribute("producto", producto);

		return "verproducto";
	}
	

	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;

		Optional<Producto> optionalProducto = productoService.get(id);
		logger.info("Producto añadido: {}", optionalProducto.get());
		logger.info("Cantidad: {}", cantidad);
		producto = optionalProducto.get();

		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * cantidad);
		detalleOrden.setProducto(producto);
		
		//validar que le producto no se añada 2 veces
		Integer idProducto=producto.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
		
		if (!ingresado) {
			detalles.add(detalleOrden);
		}
		
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "carrito";
	}


	// quitar un producto del carrito
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {

		// lista nueva de prodcutos
		List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

		for (DetalleOrden detalleOrden : detalles) {
			if (detalleOrden.getProducto().getId() != id) {
				ordenesNueva.add(detalleOrden);
			}
		}

		// poner la nueva lista con los productos restantes
		detalles = ordenesNueva;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "carrito";
	}
	
	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		//sesion
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "carrito";
	}
	
	@GetMapping("/order")
	public String order(Model model, HttpSession session) {
		
		Usuario usuario =usuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);
		
		return "pedido";
	}
	
	// guardar la orden no sirve
	/*@GetMapping("/saveOrder")
	public String saveOrder(HttpSession session ) {
		Date fecha =new Date();
		orden.setFechaCreacion(fecha);
		orden.setNumero(ordenService.generarNumeroOrden());
		
		//usuario
		Usuario usuario =usuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())  ).get();
		
		orden.setUsuario(usuario);
		ordenService.save(orden);
		
		//guardar detalles
		for (DetalleOrden dt:detalles) {
			dt.setOrden(orden);
			detalleOrdenService.save(dt);
		}
		
		///limpiar lista y orden
		orden = new Orden();
		detalles.clear();
		
		return "redirect:/";
	}*/
	
	@PostMapping("/search")
	public String searchProduct(@RequestParam String nombre, Model model) {
		logger.info("Nombre del producto: {}", nombre);
		List<Producto> productos= productoService.findAll().stream().filter( p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
		model.addAttribute("productos", productos);		
		return "home";
	}

	//producto
	@GetMapping("/show")
	public String show(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "show";
	}
	
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	//productos
	@PostMapping("/saveproducto")
	public String save(Producto producto) {
		logger.info("producto registro: {}", producto);
	
		productoService.save(producto);		
		return "redirect:/show";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Producto producto= new Producto();
		Optional<Producto> optionalProducto=productoService.get(id);
		producto= optionalProducto.get();
		
		logger.info("Producto buscado: {}",producto);
		model.addAttribute("producto", producto);
		
		return "edit";
	}
	@PostMapping("/update")
	public String update(Producto producto) {
		Producto p= new Producto();
		p=productoService.get(producto.getId()).get();
		producto.setUsuario(p.getUsuario());
		productoService.update(producto);		
		return "redirect:/show";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		productoService.delete(id);
		return "redirect:/show";
	}

	
	


}