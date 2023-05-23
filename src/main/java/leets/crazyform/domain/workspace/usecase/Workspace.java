package leets.crazyform.domain.workspace.usecase;

import leets.crazyform.global.jwt.dto.JwtResponse;

public interface Workspace {
    JwtResponse execute( String name, String handle );
}
