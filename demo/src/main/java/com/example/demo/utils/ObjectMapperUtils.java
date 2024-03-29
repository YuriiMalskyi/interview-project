package com.example.demo.utils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperUtils {
	
		private static ModelMapper modelMapper;

		static {
			modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		}

		private ObjectMapperUtils() {
		}

		public <D, T> D map(final T entity, Class<D> outClass) {
			return modelMapper.map(entity, outClass);
		}

		public <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
			return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
		}

		public <D, T> Set<D> mapAllSet(final Collection<T> entityList, Class<D> outCLass) {
			return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toSet());
		}
		
		public <S, D> D map(final S source, D destination) {
			modelMapper.map(source, destination);
			return destination;
		}
		
}
