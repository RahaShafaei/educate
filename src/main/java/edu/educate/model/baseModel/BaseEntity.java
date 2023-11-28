/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.educate.model.baseModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity<T extends BaseEntity> implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "deleted")
	private boolean deleted;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "deleted_at" , columnDefinition = "datetime")
	private LocalDateTime deletedAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "inserted_at" , columnDefinition = "datetime")
	private LocalDateTime insertedAt;

	public boolean isNew() {
		return this.id == null;
	}

	public T ifEntityIsDeleted(T baseEntity){
		return Optional.ofNullable(baseEntity).filter(e -> !e.isDeleted()).orElse(null);
	}

	public List<T> ifEntityListHasDeletedElement(List<T> baseEntities){
		if (baseEntities == null)
			return null;

		if (baseEntities.size() == 0)
			return baseEntities;

		baseEntities.removeIf(BaseEntity::isDeleted);

		return baseEntities;
	}

	@AssertTrue(message = "{general.deletedAt}")
	private boolean isValidDeletedAt() {
		if (!deleted) {
			return true;
		}

		return deletedAt != null;
	}

}
