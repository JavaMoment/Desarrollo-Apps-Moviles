package com.example.cafemenuapp

// Definimos la clase Producto que contendrá los datos de los productos del menú
data class Producto(val nombre: String, var precio: Double)

// Función para meter un nuevo producto en la lista
fun agregarProducto(productos: MutableList<Producto>) {
    println("Metele, escribí el nombre del producto:")
    val nombre = readLine()!!  // Acá agarra el nombre que escribas
    println("Ahora tirame cuánto vale:")
    val precio = readLine()!!.toDouble()  // Acá ponés el precio, solo números, no cofundirrrrrrrr
    productos.add(Producto(nombre, precio))  // Lo agrega a la lista de productos
    println("Listo el pollo, producto en cancha.")  // Avisa que ya está todo cocinado.
}


// Esta función te muestra lo que hay pa comer y tomar en el menú
fun mostrarMenu(productos: MutableList<Producto>) {
    if (productos.isEmpty()) {
        println("Che, no hay nada en el menú. Ponete las pilas y agrega algo.")
    } else {
        println("Mirá lo que tenemos pa' ofrecerte:")
        productos.forEachIndexed { index, producto ->
            println("${index + 1}. ${producto.nombre} - $${producto.precio}")  // Muestra cada producto con su precio.
        }
    }
}
// Esta va para sacar algo del menú, por si te equivocaste o qcyo
fun eliminarProducto(productos: MutableList<Producto>) {
    if (productos.isEmpty()) {
        println("Che, ni un solo producto hay para sacar. Metele algo primero.")
        return  // Corta aquí si no hay nada que eliminar.
    }
    mostrarMenu(productos)  // Primero muestra lo que hay, para saber qué número volar.
    println("Tirame el número del producto que querés dar de baja:")
    val indice = readLine()!!.toInt() - 1  // Pide el índice, ajustado para matchear la lista (que empieza en 0).
    if (indice >= 0 && indice < productos.size) {
        productos.removeAt(indice)  // Le da la baja al producto.
        println("Ya está, producto fuera del menú.")
    } else {
        println("Ese número no juega, probá con otro.")
    }
}


// Esta función es para cambiarle la ficha a algún producto que ya tengas en el menú.
fun actualizarProducto(productos: MutableList<Producto>) {
    if (productos.isEmpty()) {
        println("Che, no tenemos qué actualizar. Agrega algo antes de venir a tocar.")
        return  // Corta si no hay nada que actualizar.
    }
    mostrarMenu(productos)  // Muestra lo que hay para elegir bien qué actualizar.
    println("Decime el número del producto que vas a tunear:")
    val indice = readLine()!!.toInt() - 1  // El usuario pone el número, restamos uno para el índice.
    if (indice >= 0 && indice < productos.size) {
        println("Poné el nuevo nombre del producto:")
        val nuevoNombre = readLine()!!  // Captura el nuevo nombre que el usuario quiere poner.
        println("Ahora decime cuánto va a costar ahora:")
        val nuevoPrecio = readLine()!!.toDouble()  // Y el nuevo precio también.
        productos[indice] = Producto(nuevoNombre, nuevoPrecio)  // Actualiza los datos en la lista.
        println("Listo, producto actualizado de taquito.")
    } else {
        println("Ese número no es válido, fijate bien y probá de nuevo.")
    }
}


// La función main es el punto de entrada de la aplicación donde se maneja el menú principal
fun main() {
    val productos = mutableListOf<Producto>()
    do {
        println("""
            |Seleccione una opción:
            |1. Agregar producto
            |2. Mostrar menú
            |3. Eliminar producto
            |4. Actualizar producto
            |5. Salir
        """.trimMargin()) //Trims leading whitespace characters followed by marginPrefix from every line of a source string and removes the first and the last lines if they are blank
        val opcion = readLine()!!
        when (opcion) {
            "1" -> agregarProducto(productos)
            "2" -> mostrarMenu(productos)
            "3" -> eliminarProducto(productos)
            "4" -> actualizarProducto(productos)
            "5" -> {
                println("Saliendo de la aplicación...")
                break
            }
            else -> println("Opción no válida, por favor intente de nuevo.")
        }
    } while (true)
}
