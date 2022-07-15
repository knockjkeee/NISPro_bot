package ru.newsystems.nispro_bot.base.model.dto.callback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;

import java.util.Objects;

@Getter
@Setter
public abstract class SerializableInlineObject {

  @JsonProperty("i")
  private int index;

  public SerializableInlineObject(SerializableInlineType type) {
    this.index = type.getIndex();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SerializableInlineObject that = (SerializableInlineObject) o;
    return index == that.index;
  }

  @Override
  public int hashCode() {
    return Objects.hash( index);
  }
}
