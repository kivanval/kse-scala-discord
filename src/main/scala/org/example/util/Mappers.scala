package org.example.util

import com.fasterxml.jackson.databind.ObjectMapper
import discord4j.common.JacksonResources

object Mappers:
  def discord4j: ObjectMapper =
    JacksonResources.createFromObjectMapper(new ObjectMapper()).getObjectMapper
