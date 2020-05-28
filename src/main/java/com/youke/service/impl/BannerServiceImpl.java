package com.youke.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.Banner;
import com.youke.dao.BannerMapper;
import com.youke.service.BannerService;
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService{

}
