//package com.gql.graghql.controller.fake;
//
//import com.gql.graghql.codegen.DgsConstants;
//import com.gql.graghql.codegen.types.MobileApp;
//import com.gql.graghql.codegen.types.MobileAppFilter;
//
//import com.gql.graghql.datasource.fake.FakeMobileAppDatasource;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.SchemaMapping;
//import org.springframework.stereotype.Controller;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author : Ezekiel Eromosei
// * @code @created : 14 May, 2024
// */
//
//@Controller
//public class FakeMobileAppController {
//
//    @SchemaMapping(
//            typeName = DgsConstants.QUERY_TYPE,
//            field = DgsConstants.QUERY.MobileApps
//    )
//    public List<MobileApp> getMobileApps(@Argument(name = "mobileAppFilter") Optional<MobileAppFilter> filter) {
//
//        return filter.map(mobileAppFilter -> FakeMobileAppDatasource.MOBILE_APP_LIST
//                .stream()
//                .filter(mobileApp -> this.matchFilter(mobileAppFilter, mobileApp))
//                .toList()).orElse(FakeMobileAppDatasource.MOBILE_APP_LIST);
//
//    }
//
//    private boolean matchFilter(MobileAppFilter filter, MobileApp app) {
//        boolean isAppMatch = StringUtils.containsIgnoreCase(app.getName(),
//                StringUtils.defaultIfBlank(filter.getName(), StringUtils.EMPTY))
//                && StringUtils.containsIgnoreCase(app.getVersion(),
//                StringUtils.defaultIfBlank(filter.getVersion(), StringUtils.EMPTY))
//                && app.getReleaseDate().isAfter(
//                        Optional.ofNullable(filter.getReleaseAfter()).orElse(LocalDate.MIN))
//                && app.getDownloaded() >= Optional.ofNullable(filter.getMinimumDownload()).orElse(0);
//
//        if(!isAppMatch) return false;
//
//        if(StringUtils.isNotBlank(filter.getPlatform())
//                && !app.getPlatform().contains(filter.getPlatform().toLowerCase()))
//            return false;
//
//        if(filter.getCategory() != null && app.getCategory() != filter.getCategory())
//            return false;
//
//        return filter.getAuthor() == null
//                || StringUtils.containsIgnoreCase(app.getAuthor().getName(),
//                StringUtils.defaultIfBlank(filter.getAuthor().getName(), StringUtils.EMPTY));
//    }
//}
