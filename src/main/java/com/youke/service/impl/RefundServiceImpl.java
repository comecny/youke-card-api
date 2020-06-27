package com.youke.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.Refund;
import com.youke.dao.RefundMapper;
import com.youke.service.RefundService;
@Service
public class RefundServiceImpl extends ServiceImpl<RefundMapper, Refund> implements RefundService{

}
