package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.repository.ManagerRepository;
import ir.maktab.testmaker.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerServiceImpl implements ManagerService {

    final ManagerRepository managerRepository;
}
