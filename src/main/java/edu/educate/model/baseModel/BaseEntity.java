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
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "deleted")
	private boolean deleted;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@Column(name = "inserted_at")
	private LocalDateTime insertedAt;

	public boolean isNew() {
		return this.id == null;
	}

	@AssertTrue(message = "{general.deletedAt}")
	private boolean isValidDeletedAt() {
		if (!deleted) {
			return true;
		}

		return deletedAt != null;
	}

}
