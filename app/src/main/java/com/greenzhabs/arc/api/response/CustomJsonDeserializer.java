package com.greenzhabs.arc.api.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;
import java.util.ArrayList;

/** Saurav on 8/10/2017. */
public class CustomJsonDeserializer<T> implements JsonDeserializer<ApiResponse<T>> {

  private Gson gson = getGson();
  private Class<T> clazz;

  public CustomJsonDeserializer(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public ApiResponse<T> deserialize(
      JsonElement element, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    if (element.isJsonArray()) {
      ArrayList<T> list = new ArrayList<>();
      for (JsonElement ele : element.getAsJsonArray()) {
        list.add(gson.fromJson(ele, clazz));
      }
      return new ApiResponse<>(list);
    } else if (element.isJsonObject()) {
      JsonObject json = element.getAsJsonObject();
      if (json.has("error")) {
        ApiError error = gson.fromJson(element, ApiError.class);
        return new ApiResponse<>(error);
      } else {
        return new ApiResponse<>(gson.fromJson(element, clazz));
      }
    } else if (((JsonPrimitive) element).isString()) {
      return new ApiResponse<>(gson.fromJson(element, clazz));
    } else {
      throw new JsonParseException("Unsupported type of monument element");
    }
  }

  private Gson getGson() {
    return new GsonBuilder().create();
  }
}
