package com.ozantopuz.dicetask.shared.extension

import com.ozantopuz.dicetask.shared.reader.AssetReader
import java.io.IOException

@Throws(IOException::class)
fun asset(fileName: String) = AssetReader.getJsonDataFromAsset(fileName)
