package com.elmirov.weatherappcompose.extension

import kotlin.math.roundToInt

fun Float.tempToFormattedString(): String = "${roundToInt()}°С"