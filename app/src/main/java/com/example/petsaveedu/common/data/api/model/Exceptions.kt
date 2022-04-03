package com.example.petsaveedu.common.data.api.model

import java.io.IOException

class NetworkUnavailableException(message: String = "No network available!"): IOException(message)