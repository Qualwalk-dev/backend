package com.qualwalk.backend.typehandler;

import com.swantech.lang.core.utility.*;
import org.apache.ibatis.type.*;

import java.sql.*;
import java.util.*;

public class EnumWithCustomOrdinalTypeHandler<E extends Enum<E> & IEnumWithOrdinal<?>>
    extends EnumOrdinalTypeHandler<E> {

    private final Map<Object, E> enums = new HashMap<>();
    private Class<E> type;

    public EnumWithCustomOrdinalTypeHandler(Class<E> type) {
        super(type);
        this.type = type;
        for( E e : type.getEnumConstants() ) {
            this.enums.put(e.getOrdinal().toString(), e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        if(jdbcType == JdbcType.VARCHAR) {
            ps.setString(i, parameter.getOrdinal().toString());
        }else {
            super.setNonNullParameter(ps, i, parameter, jdbcType);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object i = rs.getObject(columnName);
        if( rs.wasNull() ) {
            return null;
        }else {
            try {
                return enums.get(i.toString());
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal name."
                );
            }
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object i = rs.getObject(columnIndex);
        if( rs.wasNull() ) {
            return null;
        }else {
            try {
                return enums.get(i.toString());
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal name."
                );
            }
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object i = cs.getObject(columnIndex);
        if( cs.wasNull() ) {
            return null;
        }else {
            try {
                return enums.get(i.toString());
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal name."
                );
            }
        }
    }

    public Class<E> getType() {
        return type;
    }
}
