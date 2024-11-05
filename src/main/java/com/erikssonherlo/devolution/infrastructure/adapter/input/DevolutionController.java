package com.erikssonherlo.devolution.infrastructure.adapter.input;

import com.erikssonherlo.common.application.response.ApiResponse;
import com.erikssonherlo.common.application.response.PaginatedResponse;
import com.erikssonherlo.devolution.application.dto.CreateDevolutionDTO;
import com.erikssonherlo.devolution.application.dto.DamagedProductReportDTO;
import com.erikssonherlo.devolution.application.dto.UpdateDevolutionDTO;
import com.erikssonherlo.devolution.domain.model.Devolution;
import com.erikssonherlo.devolution.infrastructure.port.input.*;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/devolutions")
@RequiredArgsConstructor
public class DevolutionController {

    private final FindDevolutionInputPort findDevolutionInputPort;
    private final DeleteDevolutionInputPort deleteDevolutionInputPort;
    private final CreateDevolutionInputPort createDevolutionInputPort;
    private final UpdateDevolutionInputPort updateDevolutionInputPort;
    private final GetAllDevolutionsInputPort getAllDevolutionsInputPort;
    private final GetAllDevolutionsByStatusInputPort getAllDevolutionsByStatusInputPort;
    private final ReportAllDevolutionsInputPort reportAllDevolutionsInputPort;
    private final ReportDamagedProductsInputPort reportDamagedProductsInputPort;

    @GetMapping()
    public PaginatedResponse<List<Devolution>> getAllDevolutions(
            @PageableDefault(page = 0,size = 10) Pageable pageable,
            @RequestParam(value = "storeId", required = false) List<Long> storeIds,
            @RequestParam(value = "status", required = false) String status

    ){
        Page<Devolution> devolutionsPage = getAllDevolutionsInputPort.getAllDevolutions(pageable, storeIds, status);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"SUCCESS", HttpStatus.OK,devolutionsPage.getContent(),devolutionsPage.getPageable(),devolutionsPage.isLast(),devolutionsPage.isFirst(),devolutionsPage.hasNext(),devolutionsPage.hasPrevious(),devolutionsPage.getTotalPages(),(int) devolutionsPage.getTotalElements());
    }

    @GetMapping("/status/{status}")
    public PaginatedResponse<List<Devolution>> getAllDevolutionsByStatus(@PathVariable String status, @PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<Devolution> devolutionsPage = getAllDevolutionsByStatusInputPort.getAllDevolutionsByStatus(status,pageable);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"SUCCESS", HttpStatus.OK,devolutionsPage.getContent(),devolutionsPage.getPageable(),devolutionsPage.isLast(),devolutionsPage.isFirst(),devolutionsPage.hasNext(),devolutionsPage.hasPrevious(),devolutionsPage.getTotalPages(),(int) devolutionsPage.getTotalElements());
    }

    @GetMapping("/{id}")
    public ApiResponse<Devolution> findDevolution(@PathVariable Long id){
        Devolution devolution = findDevolutionInputPort.findDevolution(id);
        return new ApiResponse<>(HttpStatus.OK.value(),"SUCCESS", HttpStatus.OK,devolution);
    }

    @PostMapping()
    public ApiResponse<Devolution> createDevolution(@RequestBody @Valid CreateDevolutionDTO createDevolutionDTO){
        return new ApiResponse<>(HttpStatus.CREATED.value(),"SUCCESS",HttpStatus.CREATED,createDevolutionInputPort.createDevolution(createDevolutionDTO));
    }

    @PutMapping("/{id}")
    public ApiResponse<Devolution> updateDevolution(@PathVariable Long id, @RequestBody @Valid UpdateDevolutionDTO updateDevolutionDTO){
        return new ApiResponse<>(HttpStatus.OK.value(),"SUCCESS",HttpStatus.OK, updateDevolutionInputPort.updateDevolution(id,updateDevolutionDTO));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteDevolution(@PathVariable Long id){
        return new ApiResponse<>(HttpStatus.NO_CONTENT.value(),"SUCCESS",HttpStatus.NO_CONTENT,deleteDevolutionInputPort.deleteDevolution(id));
    }

    @GetMapping("/reports/all-devolutions")
    public ApiResponse<List<Devolution>> reportAllDevolutions(@RequestParam(value = "storeId", required = false) List<Long> storeIds,
                                                          @RequestParam(value = "status", required = false) String status,
                                                          @RequestParam(value = "startDate", required = false) String startDate,
                                                          @RequestParam(value = "endDate", required = false) String endDate) {
        return new ApiResponse<>(HttpStatus.OK.value(), "SUCCESS", HttpStatus.OK,
                reportAllDevolutionsInputPort.reportAllDevolutions(storeIds, status, startDate, endDate));
    }

    @GetMapping("/reports/damaged-products")
    public ApiResponse<List<DamagedProductReportDTO>> getDamagedProductsReport(
            @RequestParam("storeId") Long storeId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {

        List<DamagedProductReportDTO> report = reportDamagedProductsInputPort.getDamagedProductsReport(storeId, startDate, endDate);
        return new ApiResponse<>(HttpStatus.OK.value(), "SUCCESS", HttpStatus.OK, report);
    }
}
