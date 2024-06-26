package com.example.cafemenuapp

// Definimos la clase Producto que contendrá los datos de los productos del menú
data class Producto(val nombre: String, var precio: Double)

//a ver si asi funciona esto
fun leerDouble(): Double {
    while (true) {
        try {
            return readLine()!!.toDouble().takeIf { it > 0 } ?: throw IllegalArgumentException("El precio no puede ser menor o igual a 0.")
        } catch (e: NumberFormatException) {
            println("Eso no parece un número. Intenta de nuevo:")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun leerInt(): Int {
    while (true) {
        try {
            return readLine()!!.toInt()
        } catch (e: NumberFormatException) {
            println("Debes ingresar un número entero. Intenta de nuevo:")
        }
    }
}

fun leerString(): String {
    while (true) {
        val input = readLine()!!
        if (input.isNotBlank()) return input
        println("No podes dejar esto en blanco. Intenta de nuevo:")
    }
}

// Función para meter un nuevo producto en la lista
fun agregarProducto(productos: MutableList<Producto>) {
    println("Metele, escribí el nombre del producto:")
    val nombre = leerString()  // Acá agarra el nombre que escribas
    println("Ahora tirame cuánto vale:")
    val precio = leerDouble()  // Acá ponés el precio, solo números, no confundir
    productos.add(Producto(nombre, precio))  // Lo agrega a la lista de productos
    println("Listo el pollo, producto en cancha.")  // Avisa que ya está listortiiii
}

// Esta función te muestra lo que hay para comer y tomar en el menú
fun mostrarMenu(productos: MutableList<Producto>) {
    if (productos.isEmpty()) {
        println("Che, no hay nada en el menú. Ponete las pilas y agrega algo.")
    } else {
        println("Mirá lo que tenemos para ofrecerte:")
        productos.forEachIndexed { index, producto ->
            println("${index + 1}. ${producto.nombre} - $${producto.precio}")  // Muestra cada producto con su precio
        }
    }
}

// Esta va para sacar algo del menú, por si te equivocaste
fun eliminarProducto(productos: MutableList<Producto>) {
    if (productos.isEmpty()) {
        println("Che, ni un solo producto hay para sacar. Metele algo primero.")
        return  // x si no hay productos
    }
    mostrarMenu(productos)  // Primero muestra lo que hay, para saber qué número sacar
    println("Tirame el número del producto que querés dar de baja:")
    val indice = leerInt() - 1  // Pide el índice, ajustado para matchear la lista (que empieza en 0). CUIDADO QUE EMPIEZA EN 0
    if (indice >= 0 && indice < productos.size) {
        productos.removeAt(indice)  // Le da la baja al producto
        println("Ya está, producto fuera del menú.")
    } else {
        println("Ese número no juega, probá con otro.")
    }
}

// Esta función es para cambiarle la ficha a algún producto que ya tengas en el menú
fun actualizarProducto(productos: MutableList<Producto>) {
    if (productos.isEmpty()) {
        println("Che, no tenemos qué actualizar. Agrega algo antes de venir a tocar.")
        return  // Corta si no hay nada que actualizar
    }
    mostrarMenu(productos)  // Muestra lo que hay para elegir bien qué actualizar
    println("Decime el número del producto que vas a tunear:")
    val indice = leerInt() - 1  // El usuario pone el número, restamos uno para el índice
    if (indice >= 0 && indice < productos.size) {
        println("Poné el nuevo nombre del producto:")
        val nuevoNombre = leerString()  // Captura el nuevo nombre que el usuario quiere poner
        println("Ahora decime cuánto va a costar ahora:")
        val nuevoPrecio = leerDouble()  // Y el nuevo precio también
        productos[indice] = Producto(nuevoNombre, nuevoPrecio)  // Actualiza los datos en la lista
        println("Listo, producto actualizado:).")
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
        """.trimMargin()) // Trims leading whitespace characters followed by marginPrefix from every line of a source string and removes the first and the last lines if they are blank
        val opcion = leerString()
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
