package ru.sirius.siriuswallet.data

import ru.sirius.siriuswallet.data.network.dto.CategoryDto
import ru.sirius.siriuswallet.data.network.dto.OperationDto
import ru.sirius.siriuswallet.data.network.dto.mappers.CategoryDtoMapper
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.Operation
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

fun OperationDto.toOperation(categoryDtoMapper: CategoryDtoMapper): Operation {
    return Operation(
        this.id,
        LocalDateTime.ofInstant(this.creationDate, ZoneId.of("UTC")),
        this.amount.toBigDecimal(),
        categoryDtoMapper.mapToObj(this.categoryDto)
    )
}

fun CategoryDto.toCategory(categoryDtoMapper: CategoryDtoMapper): Category {
    return categoryDtoMapper.mapToObj(this)
}

fun Operation.toOperationDto(): OperationDto {
    return OperationDto(this.id, this.accountId, this.amount.toDouble(), this.operationDate.toInstant(ZoneOffset.UTC), this.operationCategory.toCategoryDto())
}

fun Category.toCategoryDto(): CategoryDto {
    return CategoryDto(this.id, this.userId, this.name, this.type)
}